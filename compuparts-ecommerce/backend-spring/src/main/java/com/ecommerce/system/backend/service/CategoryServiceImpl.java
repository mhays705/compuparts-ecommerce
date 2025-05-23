package com.ecommerce.system.backend.service;


import com.ecommerce.system.backend.dto.category.CategoryResponse;
import com.ecommerce.system.backend.dto.category.CreateCategoryRequest;
import com.ecommerce.system.backend.entity.Category;
import com.ecommerce.system.backend.exception.CategoryNotFoundException;
import com.ecommerce.system.backend.exception.NoCategoriesFoundException;
import com.ecommerce.system.backend.mapper.CategoryMapper;
import com.ecommerce.system.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{


	private final CategoryRepository categoryRepository;
	private final CategoryMapper mapper;


	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}


	@Override
	public List<CategoryResponse> findAllCategories() {
		List<CategoryResponse> categories = mapper.toDTOList(categoryRepository.findAll());
		if (categories.isEmpty()) {
			throw new NoCategoriesFoundException("No categories found in system");
		}
		return categories;
	}

	@Override
	public CategoryResponse findCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category with id: " + id + " not found"));

		return mapper.toDTO(category);
	}

	@Override
	public CategoryResponse createCategory(CreateCategoryRequest request) {
		boolean duplicate = categoryRepository.findAll()
				.stream()
				.anyMatch(category -> category.getName().equalsIgnoreCase(request.getName()));

		if (duplicate) {
			throw new IllegalArgumentException("Category with name: " + request.getName() + " already exists");
		}

		Category category = mapper.toEntity(request);

		Category newCategory = categoryRepository.save(category);

		return mapper.toDTO(newCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		if(!categoryRepository.existsById(id)) {
			throw new CategoryNotFoundException("Category not found");
		}
		categoryRepository.deleteById(id);

	}
}
