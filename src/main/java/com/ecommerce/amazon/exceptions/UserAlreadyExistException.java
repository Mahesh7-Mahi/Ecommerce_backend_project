package com.ecommerce.amazon.exceptions;

public class UserAlreadyExistException extends RuntimeException{
	
	public UserAlreadyExistException(String message) {
		super(message);
	}

}
