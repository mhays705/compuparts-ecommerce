package com.inventorymanager.backend.dto.manufacturer;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import com.inventorymanager.backend.dto.product.ProductResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateManufacturerRequest {

	@NotBlank(message = "Manufacturer name is required")
	private String name;

}
