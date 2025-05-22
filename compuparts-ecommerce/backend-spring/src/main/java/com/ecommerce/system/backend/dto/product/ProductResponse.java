package com.ecommerce.system.backend.dto.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private Long id;

	private String name;

	private String description;

	private Double price;

	private String image;

	private Long categoryId;

	private Long manufacturerId;

	private String sku;

	private Integer stock;


}
