package com.inventorymanager.backend.repository;

import com.inventorymanager.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


	List<OrderItem> findAllByOrder_Id(Long orderId);
}
