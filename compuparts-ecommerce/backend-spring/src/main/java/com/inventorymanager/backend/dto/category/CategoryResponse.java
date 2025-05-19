package com.inventorymanager.backend.dto.category;


import com.inventorymanager.backend.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

	private Long id;
	private String name;
	private List<ProductResponse> products;
}
