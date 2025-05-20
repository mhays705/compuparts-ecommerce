package com.inventorymanager.backend.dto.product;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {


	private String name;

	private String description;

	private BigDecimal price;

	private String sku;

	private String imageUrl;

	private Long manufacturerId;

	private Long categoryId;

	@PositiveOrZero(message = "Stock cannot be less than 0")
	private Integer stock;



}
