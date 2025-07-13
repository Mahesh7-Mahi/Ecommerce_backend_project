package com.ecommerce.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.amazon.entity.Cart;


import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserId(int userId);
	
	
	@Procedure(procedureName ="sp_cart_view_based_on_cart_id")
	List<Object[]> getCartItemsBasedOnCartId(@Param("cartId") int cartId);
}
