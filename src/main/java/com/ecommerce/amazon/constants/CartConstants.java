package com.ecommerce.amazon.constants;

public class CartConstants {
	
	//API data validation
	public static final String ERROR_API_DATA_USER_ID ="User id should be greater than zero";
	public static final String ERROR_API_DATA_PRODUCT_ID ="Product id should be greater than zero";
	public static final String ERROR_API_DATA_QUANTITY ="Quantity must be greater than zero";
	
	//API SUCCESS
	public static final String SUCCESS_API_OK="OK";
	public static final String SUCCESS_API_MESSAGE="Product added to the cart successfully.";
	public static final String SUCCESS_CART_DATA_UPDATED="Cart data updated successfully.";
	public static final String SUCCESS_CART_DATA_DELETED="Product deleted successfully.";
	
	
	//API EXCEPTIONS
	public static final String EXCEPTION_EMPTY_CART="Cart items are empty";
	public static final String EXCEPTION_NOT_FOUNT_CART_ITEM="No cart item found.";
}
