package com.ecommerce.amazon.exceptions;

public class InvalidOtpForPasswordResetException extends RuntimeException{
	
	public InvalidOtpForPasswordResetException(String message) {
		super(message);
	}

}
