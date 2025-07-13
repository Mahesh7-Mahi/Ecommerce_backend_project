package com.ecommerce.amazon.exceptions;

public class EmailSendingException extends RuntimeException{
	
	public EmailSendingException(String message) {
		super(message);
	}

}
