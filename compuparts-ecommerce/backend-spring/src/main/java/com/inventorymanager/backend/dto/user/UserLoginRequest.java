package com.inventorymanager.backend.dto.user;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

	@NotBlank(message = "is required")
	private String username;

	@NotBlank(message = "is required")
	private String password;

}
