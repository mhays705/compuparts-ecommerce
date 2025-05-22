package com.ecommerce.system.backend.dto.user;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {


	@NotBlank(message = "Username is required")
	@Size(min = 5, max = 14, message = "Must be between 5 and 14 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 5, max = 100, message = "Must be between 5 and 100 characters")
	private String password;

	@NotBlank(message = "Password confirmation is required")
	@Size(min = 5, max = 100, message = "Must be between 5 and 100 characters")
	private String passwordConfirm;

	@Email(message = "Please enter valid email")
	@NotBlank(message = "Valid email is required")
	private String email;

	@NotBlank(message = "First name is required")
	@Size(max = 80, message = "must be less than 80 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 80, message = "must be less than 80 characters")
	private String lastName;

	@Size(max = 30, message = "Must be less than 30 characters")
	private String company;

}
