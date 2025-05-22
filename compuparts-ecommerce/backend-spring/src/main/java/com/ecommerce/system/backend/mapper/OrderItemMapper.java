package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.entity.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

	OrderItem toEntity(CreateOrderItemRequest createOrderItemRequest);

	OrderItemResponse toDTO(OrderItem orderItem);

	List<OrderItem> toEntityList(List<CreateOrderItemRequest> createOrderItemRequests);

	List<OrderItemResponse> toDTOList(List<OrderItem> orderItems);






}
