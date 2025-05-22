package com.ecommerce.system.backend.service;

import com.ecommerce.system.backend.dto.user.CreateUserRequest;
import com.ecommerce.system.backend.dto.user.UpdateUserRequest;
import com.ecommerce.system.backend.dto.user.UserResponse;
import com.ecommerce.system.backend.entity.Role;
import com.ecommerce.system.backend.entity.User;
import com.ecommerce.system.backend.exception.*;
import com.ecommerce.system.backend.mapper.UserMapper;
import com.ecommerce.system.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.system.backend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
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
	@Transactional(readOnly = true)
	public List<UserResponse> getAllUsers() throws NoUsersFoundException {
		List<User> users = userRepository.findAll().
				stream().toList();
		if (users.isEmpty()) {
			throw new NoUsersFoundException("No users found in the system. At least one admin is required.");
		}
		return mapper.toDTOList(users);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		return mapper.toDTO(user.orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found.")));
	}


	@Override
	@Transactional(readOnly = true)
	public UserResponse findUserByEmail(String email) throws UserNotFoundException {
		return mapper.toDTO(userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found.")));
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findByUsername(String username) {
		return mapper.toDTO(userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found.")));
	}

	@Override
	public UserResponse createCustomer(CreateUserRequest request) throws IncorrectPasswordConfirmationException {
		if (!request.getPassword().equals(request.getPasswordConfirm())) {
			throw new IncorrectPasswordConfirmationException("Password confirmation does not match.");
		}

		User user = mapper.toEntity(request);
		Role customerRole = roleRepository.findByName("ROLE_CUSTOMER").orElseThrow(() -> new RoleNotFoundException("Role CUSTOMER not found."));
		user.setRoles(Set.of(customerRole));
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);

		userRepository.save(user);

		return mapper.toDTO(user);
	}

	@Override
	public UserResponse createStaff(CreateUserRequest request) throws IncorrectPasswordConfirmationException {
		if (!request.getPassword().equals(request.getPasswordConfirm())) {
			throw new IncorrectPasswordConfirmationException("Password confirmation does not match");
		}

		User user = mapper.toEntity(request);
		Role staffRole = roleRepository.findByName("ROLE_STAFF").orElseThrow(() -> new RoleNotFoundException("Role STAFF not found"));
		user.setRoles(Set.of(staffRole));
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(true);

		userRepository.save(user);

		return mapper.toDTO(user);
	}

	@Override
	public UserResponse updateUser(Long id, UpdateUserRequest request) {
		User user = userRepository.findById(id)
				.orElseThrow((() -> new UserNotFoundException("User with id: " + id + " not found.")));
		Optional.ofNullable(request.getUsername())
				.ifPresent(username -> {
					if (userRepository.findByUsername(username).isEmpty()) {
						user.setUsername(username);
					}
					else {
						throw new InvalidUsernameException("Username: " + username + " already in use.");
					}
				});

		Optional.ofNullable(request.getEmail())
				.ifPresent(user::setEmail);

		Optional.ofNullable(request.getFirstName())
				.ifPresent(user::setFirstName);

		Optional.ofNullable(request.getLastName())
				.ifPresent(user::setLastName);

		Optional.ofNullable(request.getCompany())
				.ifPresent(user::setCompany);


		return mapper.toDTO(userRepository.save(user));
	}


	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id: " + id + " not found");
		}
		userRepository.deleteById(id);
	}
}
