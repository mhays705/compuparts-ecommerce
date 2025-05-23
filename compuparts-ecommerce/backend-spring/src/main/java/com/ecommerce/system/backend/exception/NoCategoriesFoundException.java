package com.ecommerce.system.backend.exception;

public class NoCategoriesFoundException extends RuntimeException {
	public NoCategoriesFoundException(String message) {
		super(message);
	}
}
