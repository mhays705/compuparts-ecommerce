package com.inventorymanager.backend.controller;

import com.inventorymanager.backend.dto.user.CreateUserRequest;
import com.inventorymanager.backend.dto.user.UserResponse;
import com.inventorymanager.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inventorymanager.backend.service.UserService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public ResponseEntity<List<UserResponse>> findAllUsers() {
		List<UserResponse> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/customer")
	public ResponseEntity<UserResponse> createCustomer(@RequestBody CreateUserRequest createUserRequest) {
		UserResponse user = userService.createCustomer(createUserRequest);
		URI location = URI.create("/customer/" + user.getId());
		return ResponseEntity.created(location).body(user);
	}




}
