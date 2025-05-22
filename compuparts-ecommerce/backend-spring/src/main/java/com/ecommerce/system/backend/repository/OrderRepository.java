package com.ecommerce.system.backend.repository;

import com.ecommerce.system.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllOrdersByCustomer_Id(Long id);
}
