package com.ecommerce.system.backend.dto.manufacturer;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateManufacturerRequest {

	@NotBlank(message = "Manufacturer name is required")
	private String name;

}
