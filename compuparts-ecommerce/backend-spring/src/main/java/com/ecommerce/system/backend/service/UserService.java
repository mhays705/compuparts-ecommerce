package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.user.CreateUserRequest;
import com.ecommerce.system.backend.dto.user.UpdateUserRequest;
import com.ecommerce.system.backend.dto.user.UserLoginRequest;
import com.ecommerce.system.backend.dto.user.UserResponse;

import java.util.List;


public interface UserService {

	List<UserResponse> getAllUsers();

	UserResponse findUserById(Long id);

	UserResponse findUserByEmail(String email);

	UserResponse findByUsername(String username);

	UserResponse createCustomer(CreateUserRequest request);

	UserResponse createStaff(CreateUserRequest request);

	UserResponse updateUser(Long id, UpdateUserRequest request);

	void deleteUser(Long id);

	String verify(UserLoginRequest request);


}
