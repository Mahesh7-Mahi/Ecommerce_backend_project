package com.ecommerce.amazon.pojo;

import com.ecommerce.amazon.constants.CartConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartData {
	
	//user_id,product_id,quantity
	@NotNull
	@Positive(message = CartConstants.ERROR_API_DATA_USER_ID)
	private int userId;
	
	@NotNull
	@Positive(message = CartConstants.ERROR_API_DATA_PRODUCT_ID)
	private int productId;
	
	@NotNull
	@Positive(message = CartConstants.ERROR_API_DATA_QUANTITY)
	private int quantity;

}
