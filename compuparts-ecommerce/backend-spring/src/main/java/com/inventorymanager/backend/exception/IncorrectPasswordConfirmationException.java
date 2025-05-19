package com.inventorymanager.backend.exception;

public class IncorrectPasswordConfirmationException extends RuntimeException {
	public IncorrectPasswordConfirmationException(String message) {
		super(message);
	}
}
