package com.ecommerce.amazon.exceptions;

public class InvalidPasswordException extends RuntimeException{

	public InvalidPasswordException(String message) {
		super(message);
	}
}
