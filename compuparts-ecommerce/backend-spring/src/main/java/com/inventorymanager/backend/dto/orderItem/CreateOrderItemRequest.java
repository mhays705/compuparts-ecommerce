package com.inventorymanager.backend.dto.orderItem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderItemRequest {

	@NotNull(message = "Order id is required")
	private Long orderId;

	@NotNull(message = "Product id is required")
	private Long productId;

	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity cannot be less than 1")
	private Integer quantity;

	@NotNull(message = "The price at time of order is required")
	@DecimalMin(value = "0", message = "Price cannot be less than 0")
	private BigDecimal priceAtOrder;



}
