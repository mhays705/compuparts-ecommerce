package com.inventorymanager.backend.dto.order;


import com.inventorymanager.backend.dto.orderItem.CreateOrderItemRequest;
import com.inventorymanager.backend.dto.orderItem.OrderItemResponse;
import com.inventorymanager.backend.entity.OrderItem;
import com.inventorymanager.backend.enums.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {


	@NotBlank(message = "is required")
	private String orderNumber;

	@NotNull(message = "is required")
	private Long customerId;

	@NotNull(message = "is required")
	@PastOrPresent(message = "Date must be in past or present.")
	private LocalDateTime orderDate;

	@NotNull(message = "is required")
	private OrderStatus orderStatus;

	@DecimalMin(value = "0", message = "Price cannot be less than 0")
	@NotNull(message = "is required")
	private BigDecimal totalPrice;

	@NotNull
	@Size(min = 1, message = "Order must have at least one item")
	private List<CreateOrderItemRequest> orderItems;






}
