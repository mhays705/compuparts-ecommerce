package com.inventorymanager.backend.service;

import com.inventorymanager.backend.dto.role.RoleResponse;
import com.inventorymanager.backend.dto.user.CreateUserRequest;
import com.inventorymanager.backend.dto.user.UserResponse;
import com.inventorymanager.backend.entity.Role;
import com.inventorymanager.backend.entity.User;
import com.inventorymanager.backend.exception.IncorrectPasswordConfirmationException;
import com.inventorymanager.backend.exception.NoUsersFoundException;
import com.inventorymanager.backend.exception.RoleNotFoundException;
import com.inventorymanager.backend.exception.UserNotFoundException;
import com.inventorymanager.backend.mapper.UserMapper;
import com.inventorymanager.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.inventorymanager.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper mapper, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<UserResponse> getAllUsers() throws NoUsersFoundException {
		List<User> users = userRepository.findAll().
				stream().toList();
		if (users.isEmpty()) {
			throw new NoUsersFoundException("No users found in the system");
		}
		return mapper.toDTOList(users);
	}

	@Override
	public UserResponse findUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		return mapper.toDTO(user.orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found.")));
	}


	@Override
	public UserResponse findUserByEmail(String email) throws UserNotFoundException {
		return mapper.toDTO(userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found.")));
	}


	@Override
	public UserResponse createCustomer(CreateUserRequest createUserRequest) throws IncorrectPasswordConfirmationException {
		if (!createUserRequest.getPassword().equals(createUserRequest.getPasswordConfirm())) {
			throw new IncorrectPasswordConfirmationException("Password confirmation does not match.");
		}

		User user = mapper.toEntity(createUserRequest);
		Role customerRole = roleRepository.findByName("ROLE_CUSTOMER").orElseThrow(() -> new RoleNotFoundException("Role CUSTOMER not found."));
		user.setRoles(Set.of(customerRole));
		user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		user.setEnabled(true);

		userRepository.save(user);


		return mapper.toDTO(user);

	}
}
