package com.ecommerce.system.backend.exception;

public class IncorrectPasswordConfirmationException extends RuntimeException {
	public IncorrectPasswordConfirmationException(String message) {
		super(message);
	}
}
