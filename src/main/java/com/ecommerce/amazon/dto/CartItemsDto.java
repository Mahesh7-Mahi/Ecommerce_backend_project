package com.ecommerce.amazon.dto;

import lombok.Data;

@Data
public class CartItemsDto {

	//ci.cart_item_id,ci.product_id,ci.quantity,p.title,p.description,p.price,p.images 
	
	private int cartItemId;
	
	private int productId;
	
	private int quantity;
	
	private String title;
	
	private String description;
	
	private double price;
	
	private String images;
}


