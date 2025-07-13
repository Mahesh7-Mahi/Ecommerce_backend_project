package com.ecommerce.amazon.exceptions;

public class CartItemsEmptyException extends RuntimeException{
	
	public CartItemsEmptyException(String message) {
		super(message);
	}

}
