package com.inventorymanager.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
		HttpStatus status,
		String message,
		LocalDateTime timestamp,
		String path,
		Map<String, String> errors
) {
}
