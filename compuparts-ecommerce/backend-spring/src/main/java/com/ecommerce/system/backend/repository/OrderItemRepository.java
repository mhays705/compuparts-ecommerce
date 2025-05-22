package com.ecommerce.system.backend.repository;

import com.ecommerce.system.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


	List<OrderItem> findAllByOrder_Id(Long orderId);
}
