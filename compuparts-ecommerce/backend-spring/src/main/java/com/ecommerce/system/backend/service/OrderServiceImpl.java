package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.dto.order.UpdateOrderRequest;
import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.entity.Order;
import com.ecommerce.system.backend.entity.OrderItem;
import com.ecommerce.system.backend.entity.User;
import com.ecommerce.system.backend.enums.OrderStatus;
import com.ecommerce.system.backend.exception.OrderNotFoundException;
import com.ecommerce.system.backend.exception.UserNotFoundException;
import com.ecommerce.system.backend.mapper.OrderMapper;
import com.ecommerce.system.backend.repository.OrderRepository;
import com.ecommerce.system.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper mapper;
	private final OrderItemService orderItemService;
	private final UserRepository userRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
							OrderMapper mapper,
							OrderItemService orderItemService,
							UserRepository userRepository
	) {
		this.orderRepository = orderRepository;
		this.mapper = mapper;
		this.orderItemService = orderItemService;
		this.userRepository = userRepository;

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
		User customer = userRepository.findById(request.getCustomerId())
				.orElseThrow(() -> new UserNotFoundException("Customer with id: " + request.getCustomerId() + " not found"));
		Order order = mapper.toEntity(request);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.DRAFT);
		order.setOrderItems(new ArrayList<>());
		order.setTotal(BigDecimal.ZERO);
		order.setOrderDate(LocalDate.now());
		order = orderRepository.save(order);
		return mapper.toDTO(order);
	}

	@Override
	public OrderResponse updateOrder(Long id, UpdateOrderRequest request) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
		if (!order.getStatus().equals(request.getStatus()) && request.getStatus() != null) {
			order.setStatus(request.getStatus());
			order = orderRepository.save(order);
		}
		if (request.getNewItem() != null) {
			orderItemService.createOrderItem(order.getId(), request.getNewItem());
			order = orderRepository.findById(id)
					.orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
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


}
