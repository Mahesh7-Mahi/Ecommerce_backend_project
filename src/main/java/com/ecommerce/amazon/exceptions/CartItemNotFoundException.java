package com.ecommerce.amazon.exceptions;

public class CartItemNotFoundException extends RuntimeException{
	
	private int cartItemId;
	
	public CartItemNotFoundException(String message, int cartItemId) {
		
		super(message);
		this.cartItemId=cartItemId;
	}

	public int getCartItemId() {
		return cartItemId;
	}
	

}
