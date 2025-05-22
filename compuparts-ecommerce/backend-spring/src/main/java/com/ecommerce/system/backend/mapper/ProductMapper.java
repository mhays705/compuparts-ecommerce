package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.product.CreateProductRequest;
import com.ecommerce.system.backend.dto.product.ProductResponse;
import com.ecommerce.system.backend.entity.Product;
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
