package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.dto.orderItem.UpdateOrderItemRequest;

import java.util.List;

public interface OrderItemService {

	List<OrderItemResponse> findAllItemsByOrderId(Long orderId);

	OrderItemResponse findOrderItemById(Long itemId, Long orderId);

	OrderItemResponse createOrderItem(Long orderId, CreateOrderItemRequest request);

	OrderItemResponse updateOrderItem(UpdateOrderItemRequest request, Long itemId, Long orderId);

	void deleteOrderItem(Long itemId, Long orderId);
}