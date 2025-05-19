package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product toEntity(CreateProductRequest createProductRequest);

	ProductResponse toDTO(Product product);

	List<Product> toEntityList(List<CreateProductRequest> createProductRequests);

	List<ProductResponse> toDTOList(List<Product> products);
}
