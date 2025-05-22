package com.inventorymanager.backend.dto.category;

import com.inventorymanager.backend.dto.product.CreateProductRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

	@NotBlank(message = "is required")
	private String name;

}
