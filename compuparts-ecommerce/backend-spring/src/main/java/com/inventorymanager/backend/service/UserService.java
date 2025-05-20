package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.user.CreateUserRequest;
import com.inventorymanager.backend.dto.user.UpdateUserRequest;
import com.inventorymanager.backend.dto.user.UserResponse;
import com.inventorymanager.backend.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

	List<UserResponse> getAllUsers();

	UserResponse findUserById(Long id);

	UserResponse findUserByEmail(String email);

	UserResponse findByUsername(String username);

	UserResponse createCustomer(CreateUserRequest request);

	UserResponse createStaff(CreateUserRequest request);

	UserResponse updateUser(Long id, UpdateUserRequest request);

	void deleteUser(Long id);
}
