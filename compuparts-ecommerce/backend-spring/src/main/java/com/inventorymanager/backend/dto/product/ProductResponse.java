package com.inventorymanager.backend.dto.product;


import com.inventorymanager.backend.dto.category.CategoryResponse;
import com.inventorymanager.backend.dto.manufacturer.ManufacturerResponse;
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
