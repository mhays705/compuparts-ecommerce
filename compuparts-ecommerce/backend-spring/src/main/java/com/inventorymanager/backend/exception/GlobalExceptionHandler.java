package com.inventorymanager.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(NoUsersFoundException.class)
	public ResponseEntity<ApiError> handleNoUsersFoundException(NoUsersFoundException e, HttpServletRequest request) {
		ApiError apiError = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFoundException(NoUsersFoundException e, HttpServletRequest request) {
		ApiError apiError = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
		ApiError apiError = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI()
		);
				return new ResponseEntity(apiError,HttpStatus.NOT_FOUND);
	}


}
