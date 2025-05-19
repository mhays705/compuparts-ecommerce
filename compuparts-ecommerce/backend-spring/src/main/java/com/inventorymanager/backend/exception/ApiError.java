package com.inventorymanager.backend.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
		HttpStatus status,
		String message,
		LocalDateTime timestamp,
		String path
) {
}
