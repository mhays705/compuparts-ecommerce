package com.inventorymanager.backend.repository;

import com.inventorymanager.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


	Optional<Product> findById(Long id);

	Optional<Product> findByCategory_id(Long id);

	List<Product> findAllByCategory_id(Long id);

	Optional<Product> findByManufacturer_id(Long id);

	List<Product> findAllByManufacturer_id(Long id);

}
