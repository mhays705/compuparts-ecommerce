package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.orderItem.CreateOrderItemRequest;
import com.inventorymanager.backend.dto.orderItem.OrderItemResponse;
import com.inventorymanager.backend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

	OrderItem toEntity(CreateOrderItemRequest createOrderItemRequest);

	OrderItemResponse toDTO(OrderItem orderItem);

	List<OrderItem> toEntityList(List<CreateOrderItemRequest> createOrderItemRequests);

	List<OrderItemResponse> toDTOList(List<OrderItem> orderItems);






}
