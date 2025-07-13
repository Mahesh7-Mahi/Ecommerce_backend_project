package com.ecommerce.amazon.exceptions;

public class ProductNotFoundException extends RuntimeException{

	private int productId;
	
	public ProductNotFoundException(String message,int productId) {
		super(message);
		this.productId=productId;
	}
	
	public int getProductId() {
		return this.productId;
	}
}
