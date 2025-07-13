package com.ecommerce.amazon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.amazon.constants.ProductConstants;
import com.ecommerce.amazon.entity.Product;
import com.ecommerce.amazon.exceptions.ProductNotFoundException;
import com.ecommerce.amazon.exceptions.ProductsNotFoundException;
import com.ecommerce.amazon.pojo.SearchProductApiData;
import com.ecommerce.amazon.repository.ProductRepository;

@Service
public class ProductsService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<Product> searchProducts(SearchProductApiData searchProductApiData){
		
		List<Product> products = productRepository.searchProducts(searchProductApiData.getSearchWord());
		if(products.size()==0) {
			throw new ProductsNotFoundException(ProductConstants.EXCEPTION_PRODUCT_NOT_FOUND);
		}
		return products;
	}
	
	public Product getProductDetails(int productId) {
		
		Optional<Product> dbOptionalData = productRepository.findById(productId);
		if(dbOptionalData.isEmpty()) {
			throw new ProductNotFoundException(ProductConstants.EXCEPTION_PRODUCT_NOT_FOUND,productId);
		}
		return dbOptionalData.get();
		
	}

}
