package com.inventorymanager.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	@ManyToOne
	@JoinColumn(name = "order_id" , nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price_at_order")
	private BigDecimal priceAtOrder;




}
