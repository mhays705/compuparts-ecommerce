package com.inventorymanager.backend.repository;

import com.inventorymanager.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllOrdersByCustomer_Id(Long id);
}
