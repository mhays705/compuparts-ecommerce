package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.order.CreateOrderRequest;
import com.inventorymanager.backend.dto.order.OrderResponse;
import com.inventorymanager.backend.dto.order.UpdateOrderRequest;
import com.inventorymanager.backend.dto.orderItem.CreateOrderItemRequest;
import com.inventorymanager.backend.dto.orderItem.OrderItemResponse;
import com.inventorymanager.backend.entity.Order;
import com.inventorymanager.backend.entity.OrderItem;
import com.inventorymanager.backend.enums.OrderStatus;
import com.inventorymanager.backend.exception.OrderNotFoundException;
import com.inventorymanager.backend.mapper.OrderItemMapper;
import com.inventorymanager.backend.mapper.OrderMapper;
import com.inventorymanager.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper mapper;
	private final OrderItemService orderItemService;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
							OrderMapper mapper,
							OrderItemService orderItemService) {
		this.orderRepository = orderRepository;
		this.mapper = mapper;
		this.orderItemService = orderItemService;
	}


	@Override
	@Transactional(readOnly = true)
	public List<OrderResponse> findAllOrdersByUser(Long id) {
		return mapper.toDTOList(orderRepository.findAllOrdersByCustomer_Id(id));
	}

	@Override
	@Transactional(readOnly = true)
	public OrderResponse findOrderById(Long id) {
		return mapper.toDTO(orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found")));
	}

	@Override
	public OrderResponse createOrder(CreateOrderRequest request) {
		Order order = mapper.toEntity(request);
		order.setStatus(OrderStatus.PROCESSING);
		order.setOrderItems(new ArrayList<>());
		order = orderRepository.save(order);
		return mapper.toDTO(order);
	}

	@Override
	public OrderResponse updateOrder(Long id, UpdateOrderRequest request) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
		if (!order.getStatus().equals(request.getStatus())) {
			order.setStatus(request.getStatus());
		}
		List<OrderItem> existingItems = order.getOrderItems();

		for (CreateOrderItemRequest newItem : request.getOrderItems()) {
			Optional<OrderItem> matchingItem = existingItems.stream()
					.filter(existing -> Objects.equals(existing.getProduct().getId(), newItem.getProductId()))
					.findFirst();
			if (matchingItem.isPresent()) {
				OrderItem item = matchingItem.get();
				item.setQuantity(item.getQuantity() + newItem.getQuantity());
			}
			else {
				newItem.setOrderId(order.getId());
				orderItemService.createOrderItem(newItem);
			}
		}

		order = orderRepository.save(order);

		return mapper.toDTO(order);
	}

	@Override
	public void deleteOrder(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new OrderNotFoundException("Order with id: " + id + " not found.");
		}
		orderRepository.deleteById(id);
	}


}
