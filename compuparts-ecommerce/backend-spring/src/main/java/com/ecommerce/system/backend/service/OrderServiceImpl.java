package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.dto.order.UpdateOrderRequest;
import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.entity.Order;
import com.ecommerce.system.backend.entity.OrderItem;
import com.ecommerce.system.backend.enums.OrderStatus;
import com.ecommerce.system.backend.exception.OrderNotFoundException;
import com.ecommerce.system.backend.mapper.OrderMapper;
import com.ecommerce.system.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	public List<OrderResponse> findAllOrdersByUserId(Long id) {
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
		order.setTotal(calculateOrderTotal(order.getOrderItems()));
		order = orderRepository.save(order);
		return mapper.toDTO(order);
	}

	@Override
	public OrderResponse updateOrder(Long id, UpdateOrderRequest request) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
		if (!order.getStatus().equals(request.getStatus())) {
			order.setStatus(request.getStatus());
			order = orderRepository.save(order);
		}
		return mapper.toDTO(order);
	}

	@Override
	public void deleteOrder(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new OrderNotFoundException("Order with id: " + id + " not found.");
		}
		orderRepository.deleteById(id);
	}

	private BigDecimal calculateOrderTotal(List<OrderItem> items) {

		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : items) {
			total = total.add(item.getPriceAtOrder());
		}
		return total;
	}


}
