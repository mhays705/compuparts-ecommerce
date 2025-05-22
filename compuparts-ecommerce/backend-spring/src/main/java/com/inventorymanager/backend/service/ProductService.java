package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.dto.product.UpdateProductRequest;

import java.util.List;

public interface ProductService {

	ProductResponse findProductById(Long id);

	List<ProductResponse> findAllProducts();

	ProductResponse createProduct(CreateProductRequest request);

	ProductResponse updateProduct(Long id, UpdateProductRequest request);

	List<ProductResponse> findAllByManufacturer(Long id);

	List<ProductResponse> findAllByCategory(Long id);

	void deleteProduct(Long id);





}
