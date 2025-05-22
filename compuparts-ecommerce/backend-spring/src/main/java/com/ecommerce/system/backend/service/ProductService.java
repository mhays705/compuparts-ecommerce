package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.product.CreateProductRequest;
import com.ecommerce.system.backend.dto.product.ProductResponse;
import com.ecommerce.system.backend.dto.product.UpdateProductRequest;

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
