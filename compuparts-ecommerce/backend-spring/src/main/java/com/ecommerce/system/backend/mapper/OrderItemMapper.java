package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.orderItem.CreateOrderItemRequest;
import com.ecommerce.system.backend.dto.orderItem.OrderItemResponse;
import com.ecommerce.system.backend.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


	OrderItem toEntity(CreateOrderItemRequest createOrderItemRequest);

	@Mapping(source = "order.id", target = "orderId")
	@Mapping(source = "product.id", target = "productId")
	OrderItemResponse toDTO(OrderItem orderItem);

	List<OrderItem> toEntityList(List<CreateOrderItemRequest> createOrderItemRequests);

	List<OrderItemResponse> toDTOList(List<OrderItem> orderItems);






}
