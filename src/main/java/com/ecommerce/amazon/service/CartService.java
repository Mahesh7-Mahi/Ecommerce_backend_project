package com.ecommerce.amazon.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.amazon.constants.CartConstants;
import com.ecommerce.amazon.constants.ProductConstants;
import com.ecommerce.amazon.dto.CartItemsDto;
import com.ecommerce.amazon.entity.Cart;
import com.ecommerce.amazon.entity.CartItem;
import com.ecommerce.amazon.exceptions.CartItemNotFoundException;
import com.ecommerce.amazon.exceptions.CartItemsEmptyException;
import com.ecommerce.amazon.exceptions.ProductNotFoundException;
import com.ecommerce.amazon.pojo.AddToCartData;
import com.ecommerce.amazon.pojo.UpdateCartData;
import com.ecommerce.amazon.repository.CartItemRepository;
import com.ecommerce.amazon.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public void addToCart(AddToCartData addToCartData) {
		
		Optional<Cart> dbCartOptional = cartRepository.findByUserId(addToCartData.getUserId());
		Cart cart = new Cart();
		if (dbCartOptional.isEmpty()) {
			cart.setUserId(addToCartData.getUserId());
			cart = cartRepository.save(cart);
		}else {
			cart = dbCartOptional.get();	
		}
		try {
			CartItem cartItem=new CartItem();
			cartItem.setCartId(cart.getCartId());
			cartItem.setProductId(addToCartData.getProductId());
			cartItem.setQuantity(addToCartData.getQuantity());
			cartItemRepository.save(cartItem);
		} catch (RuntimeException runtimeException) {
			throw new ProductNotFoundException(ProductConstants.EXCEPTION_PRODUCT_NOT_FOUND, addToCartData.getProductId());
		}
		
	}
	
	@Transactional
	public List<CartItemsDto> cartItemsBasedOnCartId(int userId) {
		
		Optional<Cart> dbCartOptional=cartRepository.findByUserId(userId);
		if (dbCartOptional.isEmpty()) {
			throw new CartItemsEmptyException(CartConstants.EXCEPTION_EMPTY_CART);
		}
		Cart cart = dbCartOptional.get();
		
		List<Object[]> cartDtos = cartRepository.getCartItemsBasedOnCartId(cart.getCartId());
		System.out.println(cart.getCartId());
		
		List<CartItemsDto> cartItemsList=new ArrayList<>();
		
		//ci.cart_item_id,ci.product_id,ci.quantity,p.title,p.description,p.price,p.images 
		
		//Innovation@123
		for (Object[] row: cartDtos) {
			CartItemsDto cartItemsDto = new CartItemsDto();
			cartItemsDto.setCartItemId((Integer) row[0]);
			cartItemsDto.setProductId((Integer) row[1]);
			cartItemsDto.setQuantity((Integer) row[2]);
			cartItemsDto.setTitle((String)row[3]);
			cartItemsDto.setDescription((String)row[4]);
			cartItemsDto.setPrice(Double.valueOf(String.valueOf(row[5])));
			cartItemsDto.setImages((String)row[6]);
			cartItemsList.add(cartItemsDto);
		}
		
		return cartItemsList;
		
	}
	
	public void updateCart(int cartItenId,UpdateCartData updateCartData) {
		Optional<CartItem> cartItemDataOptional = cartItemRepository.findById(cartItenId);
		if (cartItemDataOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_NOT_FOUNT_CART_ITEM, cartItenId);
		}
		CartItem cartItem=cartItemDataOptional.get();
		cartItem.setQuantity(updateCartData.getQuantity());
		cartItemRepository.save(cartItem);
	}
	
	public void deleteCart(int cartItenId) {
		Optional<CartItem> cartItemDataOptional = cartItemRepository.findById(cartItenId);
		if (cartItemDataOptional.isEmpty()) {
			throw new CartItemNotFoundException(CartConstants.EXCEPTION_NOT_FOUNT_CART_ITEM, cartItenId);
		}
		CartItem cartItem=cartItemDataOptional.get();
		cartItemRepository.delete(cartItem);
	}

}
