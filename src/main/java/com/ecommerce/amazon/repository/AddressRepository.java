package com.ecommerce.amazon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.amazon.entity.Address;
import java.util.List;
import java.util.Optional;



public interface AddressRepository extends JpaRepository<Address, Integer>{

	Optional<List<Address>> findByUserId(int userId);
	
	Optional<Address> findByUserIdAndAddressId(int userId, int addressId);
}
