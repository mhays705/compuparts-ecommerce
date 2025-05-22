package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.category.CategoryResponse;
import com.ecommerce.system.backend.dto.category.CreateCategoryRequest;
import com.ecommerce.system.backend.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category toEntity(CreateCategoryRequest createCategoryRequest);

	CategoryResponse toDTO(Category category);

	List<Category> toEntityList(List<CreateCategoryRequest> createCategoryRequests);

	List<CategoryResponse> toDTOList(List<Category> categories);


}
