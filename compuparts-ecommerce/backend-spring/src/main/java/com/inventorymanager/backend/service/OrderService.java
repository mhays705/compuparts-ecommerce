package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.order.CreateOrderRequest;
import com.inventorymanager.backend.dto.order.OrderResponse;
import com.inventorymanager.backend.dto.order.UpdateOrderRequest;

import java.util.List;

public interface OrderService {

	List<OrderResponse> findAllOrdersByUser(Long id);

	OrderResponse findOrderById(Long id);

	OrderResponse createOrder(CreateOrderRequest request);

	OrderResponse updateOrder(UpdateOrderRequest request);

	void deleteOrder(Long id);

}
