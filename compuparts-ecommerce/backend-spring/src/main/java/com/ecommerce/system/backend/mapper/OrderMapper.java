package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {


	Order toEntity(CreateOrderRequest createOrderRequest);

	OrderResponse toDTO(Order order);

	List<Order> toEntityList(List<CreateOrderRequest> createOrderRequests);

	List<OrderResponse> toDTOList(List<Order> orders);





}
