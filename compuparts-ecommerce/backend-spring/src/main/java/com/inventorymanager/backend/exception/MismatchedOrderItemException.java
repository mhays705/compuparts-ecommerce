package com.inventorymanager.backend.exception;

public class MismatchedOrderItemException extends RuntimeException {
	public MismatchedOrderItemException(String message) {
		super(message);
	}
}
