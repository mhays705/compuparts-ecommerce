package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(source = "categoryId", target = "category.id")
	@Mapping(source = "manufacturerId", target = "manufacturer.id")
	Product toEntity(CreateProductRequest createProductRequest);

	@Mapping(source = "category.id", target = "categoryId")
	@Mapping(source = "manufacturer.id", target = "manufacturerId")

	ProductResponse toDTO(Product product);

	List<Product> toEntityList(List<CreateProductRequest> createProductRequests);

	List<ProductResponse> toDTOList(List<Product> products);
}
