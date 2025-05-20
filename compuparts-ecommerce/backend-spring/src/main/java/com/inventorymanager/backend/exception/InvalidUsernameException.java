package com.inventorymanager.backend.exception;

public class InvalidUsernameException extends RuntimeException {
	public InvalidUsernameException(String message) {
		super(message);
	}
}
