package com.inventorymanager.backend.exception;

public class NoUsersFoundException extends RuntimeException {
	public NoUsersFoundException(String message) {
		super(message);
	}
}
