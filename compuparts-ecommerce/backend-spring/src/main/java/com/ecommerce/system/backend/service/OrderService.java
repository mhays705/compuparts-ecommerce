package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.dto.order.UpdateOrderRequest;

import java.util.List;

public interface OrderService {

	List<OrderResponse> findAllOrdersByUserId(Long id);

	OrderResponse findOrderById(Long id);

	OrderResponse createOrder(CreateOrderRequest request);

	OrderResponse updateOrder(Long id, UpdateOrderRequest request);

	void deleteOrder(Long id);


}
