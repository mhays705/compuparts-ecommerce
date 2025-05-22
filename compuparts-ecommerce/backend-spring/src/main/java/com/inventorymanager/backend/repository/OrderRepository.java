package com.inventorymanager.backend.repository;

import com.inventorymanager.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
