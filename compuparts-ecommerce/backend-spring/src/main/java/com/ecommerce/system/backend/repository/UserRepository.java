package com.ecommerce.system.backend.repository;

import com.ecommerce.system.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);

	boolean existsByUsername(String username);
}
