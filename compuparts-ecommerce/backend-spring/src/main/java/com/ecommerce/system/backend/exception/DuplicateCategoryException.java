package com.ecommerce.system.backend.exception;

public class DuplicateCategoryException extends RuntimeException {
	public DuplicateCategoryException(String message) {
		super(message);
	}
}
