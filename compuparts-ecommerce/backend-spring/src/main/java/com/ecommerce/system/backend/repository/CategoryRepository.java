package com.ecommerce.system.backend.repository;

import com.ecommerce.system.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
