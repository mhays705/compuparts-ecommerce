package com.ecommerce.system.backend.dto.order;

import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderRequest {

	private OrderStatus status;
	private List<CreateOrderItemRequest> orderItems;
}
