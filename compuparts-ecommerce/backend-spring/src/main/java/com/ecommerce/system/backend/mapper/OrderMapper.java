package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.order.CreateOrderRequest;
import com.ecommerce.system.backend.dto.order.OrderResponse;
import com.ecommerce.system.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {


	Order toEntity(CreateOrderRequest createOrderRequest);

	@Mapping(source = "id", target = "orderId")
	@Mapping(source = "customer.id", target = "customerId")
	@Mapping(source = "orderItems", target = "orderItems")
	OrderResponse toDTO(Order order);

	List<Order> toEntityList(List<CreateOrderRequest> createOrderRequests);

	List<OrderResponse> toDTOList(List<Order> orders);





}
