package com.inventorymanager.backend.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {


	@Size(min = 5, max = 14, message = "Must be between 5 and 14 characters")
	private String username;

	@Email(message = "Please enter valid email")
	private String email;

	@Size(max = 80, message = "must be less than 80 characters")
	private String firstName;

	@Size(max = 80, message = "must be less than 80 characters")
	private String lastName;

	@Size(max = 30, message = "Must be less than 30 characters")
	private String company;


}
