package com.ecommerce.system.backend.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
}
