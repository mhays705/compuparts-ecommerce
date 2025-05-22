package com.ecommerce.system.backend.controller;


import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.dto.order.UpdateOrderRequest;
import com.ecommerce.system.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}


	@GetMapping("/find-all/{id}")
	public ResponseEntity<List<OrderResponse>> findAllOrdersByUser(@PathVariable Long id) {
		List<OrderResponse> orders = orderService.findAllOrdersByUserId(id);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> findOrderById(@PathVariable Long id) {
		OrderResponse order = orderService.findOrderById(id);
		return ResponseEntity.ok(order);
	}

	@PostMapping()
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
		OrderResponse newOrder = orderService.createOrder(request);
		URI location = URI.create("/api/orders/" + newOrder.getOrderId());
		return ResponseEntity.created(location).body(newOrder);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody UpdateOrderRequest request, @PathVariable Long id) {
		OrderResponse order = orderService.updateOrder(id, request);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}



}
