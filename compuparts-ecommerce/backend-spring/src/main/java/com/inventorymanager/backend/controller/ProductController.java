package com.inventorymanager.backend.controller;


import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.dto.product.UpdateProductRequest;
import com.inventorymanager.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> products = productService.findAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
		ProductResponse product = productService.findProductById(id);
		return ResponseEntity.ok(product);
	}

	@PostMapping()
	public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
		ProductResponse product = productService.createProduct(request);
		URI location = URI.create("/api/products/" + product.getId());
		return ResponseEntity.created(location).body(product);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,@Valid @RequestBody UpdateProductRequest request) {
		ProductResponse updatedProduct = productService.updateProduct(id, request);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

}
