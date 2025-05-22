package com.inventorymanager.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(Exception e,
														   HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.INTERNAL_SERVER_ERROR,
				"An unexpected error occurred.",
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}


	@ExceptionHandler(NoUsersFoundException.class)
	public ResponseEntity<ApiError> handleNoUsersFoundException(NoUsersFoundException e,
																HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}


	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e,
																HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e,
																	HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(IncorrectPasswordConfirmationException.class)
	public ResponseEntity<ApiError> handleIncorrectPasswordConfirmationException(IncorrectPasswordConfirmationException e,
																				 HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(InvalidUsernameException.class)
	public ResponseEntity<ApiError> handleInvalidUsernameException(InvalidUsernameException e,
																   HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());
	}


	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<ApiError> handleRoleNotFoundException(RoleNotFoundException e,
																HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
																		  HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();

		e.getBindingResult().getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
				});

			ApiError error = new ApiError(
					HttpStatus.BAD_REQUEST,
					"Validation Failed",
					LocalDateTime.now(),
					request.getRequestURI(),
					errors
			);

			return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException e,
																   HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());

	}

	@ExceptionHandler(ManufacturerNotFoundException.class)
	public ResponseEntity<ApiError> handleManufacturerNotFoundException(ManufacturerNotFoundException e,
																		HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ApiError> handleOrderNotFoundException(OrderNotFoundException e,
																 HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<ApiError> handleOrderItemNotFoundException(OrderItemNotFoundException e,
																	 HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);

		return new ResponseEntity<>(error, error.status());
	}

	@ExceptionHandler(MismatchedOrderItemException.class)
	public ResponseEntity<ApiError> handleIllegalArgumentException(MismatchedOrderItemException e,
																   HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST,
				e.getMessage(),
				LocalDateTime.now(),
				request.getRequestURI(),
				null
		);
		return new ResponseEntity<>(error, error.status());
	}

}
