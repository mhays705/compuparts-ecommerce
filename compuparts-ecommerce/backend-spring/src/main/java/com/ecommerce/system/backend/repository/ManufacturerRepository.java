package com.ecommerce.system.backend.repository;

import com.ecommerce.system.backend.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
