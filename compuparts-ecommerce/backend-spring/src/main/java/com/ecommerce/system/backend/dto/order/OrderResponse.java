package com.ecommerce.system.backend.dto.order;

import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {


	private Long orderId;

	private String orderNumber;

	private Long customerId;

	private LocalDate orderDate;

	private OrderStatus status;

	private BigDecimal total;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private List<OrderItemResponse> orderItems;






}
