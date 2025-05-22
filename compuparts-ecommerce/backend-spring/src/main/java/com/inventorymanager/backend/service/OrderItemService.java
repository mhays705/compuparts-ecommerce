package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.order.CreateOrderRequest;
import com.inventorymanager.backend.dto.orderItem.CreateOrderItemRequest;
import com.inventorymanager.backend.dto.orderItem.OrderItemResponse;
import com.inventorymanager.backend.dto.orderItem.UpdateOrderItemRequest;
import com.inventorymanager.backend.entity.Order;

import java.util.List;

public interface OrderItemService {

	List<OrderItemResponse> findAllItemsByOrderId(Long orderId);

	OrderItemResponse findOrderItemById(Long itemId);

	OrderItemResponse createOrderItem(CreateOrderItemRequest request);

	OrderItemResponse updateOrderItem(UpdateOrderItemRequest request, Long itemId);

	void deleteOrderItem(Long itemId, Long orderId);
}