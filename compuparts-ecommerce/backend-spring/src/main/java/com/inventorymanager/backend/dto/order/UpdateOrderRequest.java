package com.inventorymanager.backend.dto.order;

import com.inventorymanager.backend.dto.orderItem.CreateOrderItemRequest;
import com.inventorymanager.backend.enums.OrderStatus;
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
