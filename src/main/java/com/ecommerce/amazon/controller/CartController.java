package com.ecommerce.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.amazon.constants.CartConstants;
import com.ecommerce.amazon.dto.CartItemsDto;
import com.ecommerce.amazon.payload.ApiResponse;
import com.ecommerce.amazon.pojo.AddToCartData;
import com.ecommerce.amazon.pojo.UpdateCartData;
import com.ecommerce.amazon.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<String>> addToCart(@Valid @RequestBody AddToCartData addToCartData) {
		
		cartService.addToCart(addToCartData);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_API_MESSAGE);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/view/{userId}")
	public ResponseEntity<ApiResponse<List<CartItemsDto>>> getMethodName(@PathVariable int userId) {
		
		List<CartItemsDto> cartItemsList = cartService.cartItemsBasedOnCartId(userId);
		
		ApiResponse<List<CartItemsDto>> apiResponse = new ApiResponse<>(true,CartConstants.SUCCESS_API_OK,cartItemsList);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse<String>> updateCart(@PathVariable int cartItemId, @RequestBody UpdateCartData updateCartData){
	
		cartService.updateCart(cartItemId, updateCartData);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_CART_DATA_UPDATED);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse<String>> deleteCartItem(@PathVariable int cartItemId){
		
		cartService.deleteCart(cartItemId);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,CartConstants.SUCCESS_API_OK,CartConstants.SUCCESS_CART_DATA_DELETED);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
