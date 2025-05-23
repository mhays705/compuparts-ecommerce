package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.category.CategoryResponse;
import com.ecommerce.system.backend.dto.category.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

	List<CategoryResponse> findAllCategories();

	CategoryResponse findCategoryById(Long id);

	CategoryResponse createCategory(CreateCategoryRequest request);

	void deleteCategory(Long id);


}
