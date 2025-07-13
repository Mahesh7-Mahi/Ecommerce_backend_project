package com.ecommerce.amazon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.amazon.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	//sp_search_products_data
	@Procedure(procedureName = "sp_search_products_data")
	List<Product> searchProducts(@Param("inp_search_word") String searchWord);
	
	

}
