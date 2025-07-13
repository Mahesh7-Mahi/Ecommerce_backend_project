package com.ecommerce.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.amazon.constants.AuthConstants;
import com.ecommerce.amazon.constants.ProductConstants;
import com.ecommerce.amazon.entity.Product;
import com.ecommerce.amazon.payload.ApiResponse;
import com.ecommerce.amazon.pojo.SearchProductApiData;
import com.ecommerce.amazon.service.ProductsService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	@PostMapping("/search")
	public ResponseEntity<ApiResponse<List<Product>>> searchProduct(@RequestBody SearchProductApiData searchProductApiData) {
		
		List<Product> products = productsService.searchProducts(searchProductApiData);
		
		ApiResponse<List<Product>> apiResponse = new ApiResponse<>(true,ProductConstants.PRODUCT_SEARCH_SUCCESS,products);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<Product>> productDetails(@PathVariable int productId) {
		
		Product product = productsService.getProductDetails(productId);
		
		ApiResponse<Product> apiResponse = new ApiResponse<>(true,ProductConstants.PRODUCT_API_SUCCESS,product);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	
}
