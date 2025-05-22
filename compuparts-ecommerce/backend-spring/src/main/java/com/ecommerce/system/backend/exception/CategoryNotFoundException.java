package com.ecommerce.system.backend.exception;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
