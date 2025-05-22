package com.inventorymanager.backend.dto.orderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

	private Long id;

	private Long orderId;

	private Long productId;

	private Integer quantity;

	private BigDecimal priceAtOrder;




}
