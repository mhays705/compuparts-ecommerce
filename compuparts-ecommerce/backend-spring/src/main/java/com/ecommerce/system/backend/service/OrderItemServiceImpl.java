package com.ecommerce.system.backend.service;


import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.dto.orderItem.UpdateOrderItemRequest;
import com.ecommerce.system.backend.entity.Order;
import com.ecommerce.system.backend.entity.OrderItem;
import com.ecommerce.system.backend.entity.Product;
import com.ecommerce.system.backend.exception.MismatchedOrderItemException;
import com.ecommerce.system.backend.exception.OrderItemNotFoundException;
import com.ecommerce.system.backend.exception.OrderNotFoundException;
import com.ecommerce.system.backend.exception.ProductNotFoundException;
import com.ecommerce.system.backend.mapper.OrderItemMapper;
import com.ecommerce.system.backend.repository.OrderItemRepository;
import com.ecommerce.system.backend.repository.OrderRepository;
import com.ecommerce.system.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderItemMapper mapper;

	@Autowired
	public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
								OrderRepository orderRepository,
								ProductRepository productRepository,
								OrderItemMapper mapper) {
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderItemResponse> findAllItemsByOrderId(Long orderId) {
		if (!orderRepository.existsById(orderId))
			throw new OrderNotFoundException("Order id: " + orderId + " not found");
		return mapper.toDTOList(orderItemRepository.findAllByOrder_Id(orderId));
	}

	@Override
	@Transactional(readOnly = true)
	public OrderItemResponse findOrderItemById(Long itemId) {
		OrderItem item = orderItemRepository.findById(itemId)
				.orElseThrow(() -> new OrderItemNotFoundException("Order item with id: " + itemId + " not found"));
		return mapper.toDTO(item);
	}

	@Override
	public OrderItemResponse createOrderItem(CreateOrderItemRequest request) {
		Order order = orderRepository.findById(request.getOrderId())
				.orElseThrow(() -> new OrderNotFoundException("Order id: " + request.getOrderId()+ " not found"));
		Product product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));
		OrderItem newItem = mapper.toEntity(request);
		newItem.setOrder(order);
		newItem.setProduct(product);
		orderItemRepository.save(newItem);
		return mapper.toDTO(newItem);
	}

	@Override
	public OrderItemResponse updateOrderItem(UpdateOrderItemRequest request, Long itemId) {
		OrderItem item = orderItemRepository.findById(itemId)
				.orElseThrow(() -> new OrderItemNotFoundException("Order item with id: " + itemId + " not found"));
		item.setQuantity(request.getQuantity());
		orderItemRepository.save(item);
		return mapper.toDTO(item);
	}

	@Override
	public void deleteOrderItem(Long itemId, Long orderId) {
		OrderItem item = orderItemRepository.findById(itemId)
				.orElseThrow(() -> new OrderItemNotFoundException("Order item not found"));
		if (!Objects.equals(item.getOrder().getId(), orderId)) {
			throw new MismatchedOrderItemException("Order item does not belong to order");
		}
		orderItemRepository.delete(item);
	}
}
