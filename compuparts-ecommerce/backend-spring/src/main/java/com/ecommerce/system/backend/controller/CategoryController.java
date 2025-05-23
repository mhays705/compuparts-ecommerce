package com.ecommerce.system.backend.controller;


import com.ecommerce.system.backend.dto.category.CategoryResponse;
import com.ecommerce.system.backend.dto.category.CreateCategoryRequest;
import com.ecommerce.system.backend.repository.CategoryRepository;
import com.ecommerce.system.backend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public ResponseEntity<List<CategoryResponse>> findAllCategories() {
		List<CategoryResponse> categories = categoryService.findAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
		CategoryResponse category = categoryService.findCategoryById(id);
		return ResponseEntity.ok(category);
	}

	@PostMapping()
	public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
		CategoryResponse newCategory = categoryService.createCategory(request);
		URI location = URI.create("/api/categories/" + newCategory.getId());
		return ResponseEntity.created(location).body(newCategory);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}


}
