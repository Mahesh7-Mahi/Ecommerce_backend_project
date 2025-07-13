package com.ecommerce.amazon.exceptions;

public class UserNotFound extends RuntimeException{

	public UserNotFound(String message) {
		super(message);
	}
}
