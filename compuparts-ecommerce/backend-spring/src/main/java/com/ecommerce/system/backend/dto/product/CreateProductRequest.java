package com.ecommerce.system.backend.dto.product;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {


	@NotBlank(message = "Product name is required")
	private String name;

	private String description;

	@NotNull(message = "Product price is required")
	private BigDecimal price;

	@NotBlank(message = "Product sku is required")
	private String sku;

	private String imageUrl;

	@NotNull(message = "Manufacturer id is required")
	private Long manufacturerId;

	@NotNull(message = "Category id is required")
	private Long categoryId;

	@PositiveOrZero(message = "Stock cannot be less than 0")
	private Integer stock;

}
