package com.inventorymanager.backend.dto.manufacturer;

import com.inventorymanager.backend.dto.product.ProductResponse;
import com.inventorymanager.backend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerResponse {

	private Long id;
	private String name;

}
