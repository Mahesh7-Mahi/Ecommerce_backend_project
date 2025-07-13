package com.ecommerce.amazon.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.amazon.constants.ExceptionConstants;
import com.ecommerce.amazon.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> errors = new HashMap<>();
		
		methodArgumentNotValidException.getBindingResult().getFieldErrors()
										.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		ApiResponse<Map<String, String>> response = new ApiResponse<>(false,ExceptionConstants.API_FAILED,errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception exception){
		
		ApiResponse<String> response = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ApiResponse<String>> userNotFoundException(UserNotFound userNotFound){
		ApiResponse<String> userNotFoundResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,userNotFound.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundResponse);
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ApiResponse<String>> invalidPasswordException(InvalidPasswordException invalidPasswordException){
		ApiResponse<String> invalidPasswordExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,invalidPasswordException.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidPasswordExceptionResponse);
	}
	
	@ExceptionHandler(EmailSendingException.class)
	public ResponseEntity<ApiResponse<String>> emailSendingException(EmailSendingException emailSendingException){
		ApiResponse<String> emailSendingExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,emailSendingException.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailSendingExceptionResponse);
	}
	
	@ExceptionHandler(InvalidOtpForPasswordResetException.class)
	public ResponseEntity<ApiResponse<String>> invalidOtpForPasswordResetException(InvalidOtpForPasswordResetException invalidOtpForPasswordResetException){
		ApiResponse<String> invalidOtpForPasswordResetExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,invalidOtpForPasswordResetException.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidOtpForPasswordResetExceptionResponse);
	}
	
	@ExceptionHandler(ProductsNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> productsNotFoundException(ProductsNotFoundException productsNotFoundException){
		ApiResponse<String> productsNotFoundExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,productsNotFoundException.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productsNotFoundExceptionResponse);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> productNotFoundException(ProductNotFoundException productNotFoundException){
		ApiResponse<String> productNotFoundExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,productNotFoundException.getMessage()+"productId: "+ productNotFoundException.getProductId());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFoundExceptionResponse);
	}
	
	@ExceptionHandler(CartItemsEmptyException.class)
	public ResponseEntity<ApiResponse<String>> cartItemsEmptyException(CartItemsEmptyException cartItemsEmptyException){
		ApiResponse<String> cartItemsEmptyExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,cartItemsEmptyException.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartItemsEmptyExceptionResponse);
	}
	
	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> cartItemNotFoundException(CartItemNotFoundException cartItemNotFoundException){
		ApiResponse<String> cartItemNotFoundExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,cartItemNotFoundException.getMessage()+"cartItemId: "+ cartItemNotFoundException.getCartItemId());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cartItemNotFoundExceptionResponse);
	}
	
	@ExceptionHandler(AddressEmptyException.class)
	public ResponseEntity<ApiResponse<String>> AddressEmptyException(AddressEmptyException addressEmptyException){
		ApiResponse<String> addressEmptyExceptionResponse = new ApiResponse<>(false,ExceptionConstants.UNABLE_TO_PROCESS_YOUR_REQUEST,addressEmptyException.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(addressEmptyExceptionResponse);
	}

}
