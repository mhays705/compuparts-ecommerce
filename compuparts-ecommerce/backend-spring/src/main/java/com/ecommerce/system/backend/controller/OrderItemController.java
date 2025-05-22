package com.ecommerce.system.backend.controller;


import com.ecommerce.system.backend.dto.order.UpdateOrderRequest;
import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.dto.orderItem.UpdateOrderItemRequest;
import com.ecommerce.system.backend.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/items")
public class OrderItemController {

	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@GetMapping()
	public ResponseEntity<List<OrderItemResponse>> findAllItemsByOrderId(@PathVariable Long orderId) {
		List<OrderItemResponse> orderItems = orderItemService.findAllItemsByOrderId(orderId);
		return ResponseEntity.ok(orderItems);
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<OrderItemResponse> findItemById(@PathVariable Long itemId, @PathVariable Long orderId) {
		OrderItemResponse item = orderItemService.findOrderItemById(itemId, orderId);
		return ResponseEntity.ok(item);
	}

	@PostMapping()
	public ResponseEntity<OrderItemResponse> createOrderItem(@PathVariable Long orderId,
															 @Valid @RequestBody CreateOrderItemRequest request) {
		OrderItemResponse newOrder = orderItemService.createOrderItem(orderId, request);
		URI location = URI.create("/api/orders/" + orderId + "/items/" + newOrder.getId());
		return ResponseEntity.created(location).body(newOrder);
	}

	@PatchMapping("/{itemId}")
	public ResponseEntity<OrderItemResponse> updateOrderItem(@Valid @RequestBody UpdateOrderItemRequest request,
															 @PathVariable Long itemId,
															 @PathVariable Long orderId) {
		OrderItemResponse updatedOrder = orderItemService.updateOrderItem(request, itemId, orderId);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable Long itemId, @PathVariable Long orderId) {
		orderItemService.deleteOrderItem(itemId, orderId);
		return ResponseEntity.noContent().build();
	}


}
