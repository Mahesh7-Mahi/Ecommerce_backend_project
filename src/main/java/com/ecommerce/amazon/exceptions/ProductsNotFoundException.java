package com.ecommerce.amazon.exceptions;

public class ProductsNotFoundException extends RuntimeException{
	
	public ProductsNotFoundException(String message) {
		super(message);
	}

}
