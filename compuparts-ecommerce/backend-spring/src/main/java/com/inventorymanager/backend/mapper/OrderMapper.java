package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.order.CreateOrderRequest;
import com.inventorymanager.backend.dto.order.OrderResponse;
import com.inventorymanager.backend.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {


	Order toEntity(CreateOrderRequest createOrderRequest);

	OrderResponse toDTO(Order order);

	List<Order> toEntityList(List<CreateOrderRequest> createOrderRequests);

	List<OrderResponse> toDTOList(List<Order> orders);





}
