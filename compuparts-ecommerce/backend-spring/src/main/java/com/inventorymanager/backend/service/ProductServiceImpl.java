package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.dto.product.UpdateProductRequest;
import com.inventorymanager.backend.entity.Category;
import com.inventorymanager.backend.entity.Manufacturer;
import com.inventorymanager.backend.entity.Product;
import com.inventorymanager.backend.exception.CategoryNotFoundException;
import com.inventorymanager.backend.exception.ManufacturerNotFoundException;
import com.inventorymanager.backend.exception.ProductNotFoundException;
import com.inventorymanager.backend.mapper.ProductMapper;
import com.inventorymanager.backend.repository.CategoryRepository;
import com.inventorymanager.backend.repository.ManufacturerRepository;
import com.inventorymanager.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper mapper;
	private final ManufacturerRepository manufacturerRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
							  ProductMapper mapper,
							  ManufacturerRepository manufacturerRepository,
							  CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.mapper = mapper;
		this.manufacturerRepository = manufacturerRepository;
		this.categoryRepository = categoryRepository;
	}
	@Transactional(readOnly = true)
	@Override
	public ProductResponse findProductById(Long id) {
		return mapper.toDTO(productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found.")));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductResponse> findAllProducts() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found.");
		}
		return mapper.toDTOList(products);
	}

	@Override
	public ProductResponse createProduct(CreateProductRequest request) {
		Product product = mapper.toEntity(request);
		productRepository.save(product);
		return mapper.toDTO(product);
	}

	@Override
	public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found."));
		Optional.ofNullable(request.getName())
				.ifPresent(product::setName);

		Optional.ofNullable(request.getDescription())
				.ifPresent(product::setDescription);

		Optional.ofNullable(request.getPrice())
				.ifPresent(product::setPrice);

		Optional.ofNullable(request.getSku())
				.ifPresent(product::setSku);

		Optional.ofNullable(request.getImageUrl())
				.ifPresent(product::setImageUrl);

		if (request.getManufacturerId() != null) {
			Manufacturer manufacturer = manufacturerRepository.findById(request.getManufacturerId())
					.orElseThrow(() -> new ManufacturerNotFoundException("Manufacturer with id: " + request.getManufacturerId() + " not found."));
			product.setManufacturer(manufacturer);
		}

		if (request.getCategoryId() != null) {
			Category category = categoryRepository.findById(request.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException("Category with id: " + request.getCategoryId() + " not found."));
			product.setCategory(category);
		}

		Optional.ofNullable(request.getStock())
				.ifPresent(product::setStock);

		productRepository.save(product);
		return mapper.toDTO(product);
	}

	@Override
	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException("Product with id: " + id + " not found.");
		}
		productRepository.deleteById(id);
	}
}
