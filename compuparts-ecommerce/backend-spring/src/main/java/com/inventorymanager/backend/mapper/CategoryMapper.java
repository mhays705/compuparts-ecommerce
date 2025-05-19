package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.category.CategoryResponse;
import com.inventorymanager.backend.dto.category.CreateCategoryRequest;
import com.inventorymanager.backend.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category toEntity(CreateCategoryRequest createCategoryRequest);

	CategoryResponse toDTO(Category category);

	List<Category> toEntityList(List<CreateCategoryRequest> createCategoryRequests);

	List<CategoryResponse> toDTOList(List<Category> categories);


}
