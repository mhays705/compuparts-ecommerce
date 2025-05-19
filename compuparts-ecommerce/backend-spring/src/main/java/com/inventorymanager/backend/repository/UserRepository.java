package com.inventorymanager.backend.repository;

import com.inventorymanager.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);
}
