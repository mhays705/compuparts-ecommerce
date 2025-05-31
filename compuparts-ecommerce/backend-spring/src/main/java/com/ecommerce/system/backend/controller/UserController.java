package com.ecommerce.system.backend.controller;

import com.ecommerce.system.backend.dto.jwt.JwtResponse;
import com.ecommerce.system.backend.dto.user.CreateUserRequest;
import com.ecommerce.system.backend.dto.user.UpdateUserRequest;
import com.ecommerce.system.backend.dto.user.UserLoginRequest;
import com.ecommerce.system.backend.dto.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.system.backend.service.UserService;


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

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable Long id ) {
		UserResponse user = userService.findUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/by-email")
	public ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email) {
		UserResponse user = userService.findUserByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/by-username")
	public ResponseEntity<UserResponse> findUserByUsername(@RequestParam String username) {
		UserResponse user = userService.findByUsername(username);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/customers")
	public ResponseEntity<UserResponse> createCustomer(@Valid @RequestBody CreateUserRequest createUserRequest) {
		UserResponse user = userService.createCustomer(createUserRequest);
		URI location = URI.create("/api/users/" + user.getId());
		return ResponseEntity.created(location).body(user);
	}

	@PostMapping("/staff")
	public ResponseEntity<UserResponse> createStaff(@Valid @RequestBody CreateUserRequest createUserRequest) {
		UserResponse user = userService.createStaff(createUserRequest);
		URI location = URI.create("/api/users/" + user.getId());
		return ResponseEntity.created(location).body(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@Valid @RequestBody UpdateUserRequest updateUserRequest) {
		UserResponse updatedUser = userService.updateUser(id, updateUserRequest);
		return ResponseEntity.ok().body(updatedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest request) {
		String token = userService.verify(request);
		return ResponseEntity.ok(new JwtResponse(token));
	}






}
