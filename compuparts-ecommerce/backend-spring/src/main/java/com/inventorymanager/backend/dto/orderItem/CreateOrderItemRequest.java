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

	@NotNull(message = "is required")
	private Long orderId;

	@NotNull(message = "is required")
	private Long productId;

	@Min(value = 1, message = "Quantity cannot be less than 1")
	private int quantity;

	@NotNull(message = "is required")
	@DecimalMin(value = "0", message = "Price cannot be less than 0")
	private BigDecimal priceAtOrder;



}
