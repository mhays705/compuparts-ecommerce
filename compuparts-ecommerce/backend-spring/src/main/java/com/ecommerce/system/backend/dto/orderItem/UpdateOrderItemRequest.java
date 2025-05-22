package com.ecommerce.system.backend.dto.orderItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderItemRequest {


	@Min(value = 1, message = "Quantity cannot be less than 1")
	private Integer quantity;
}
