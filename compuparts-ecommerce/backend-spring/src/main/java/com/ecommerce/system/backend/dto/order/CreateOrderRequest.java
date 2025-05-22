package com.ecommerce.system.backend.dto.order;


import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.enums.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {


	@NotBlank(message = "Order number is required")
	private String orderNumber;

	@NotNull(message = "Customer id is required")
	private Long customerId;

	@NotNull(message = "Order date is required")
	@PastOrPresent(message = "Date must be in past or present.")
	private LocalDateTime orderDate;

	@NotNull(message = "Order status is required")
	private OrderStatus status;

	@NotNull(message = "Order must have at least one item")
	@Size(min = 1, message = "Order must have at least one item")
	private List<CreateOrderItemRequest> orderItems;






}
