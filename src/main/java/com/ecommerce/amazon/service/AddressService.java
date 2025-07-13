package com.ecommerce.amazon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.amazon.constants.AddressConstants;
import com.ecommerce.amazon.entity.Address;
import com.ecommerce.amazon.enums.AddressType;
import com.ecommerce.amazon.exceptions.AddressEmptyException;
import com.ecommerce.amazon.pojo.AddressAddDataApi;
import com.ecommerce.amazon.repository.AddressRepository;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address addAddress(AddressAddDataApi addressAddDataApi) {
		
		
		Address address = new Address();
		address.setUserId(addressAddDataApi.getUser_id());
		address.setAddressLine1(addressAddDataApi.getAddress_line1());
		address.setAddressLine2(addressAddDataApi.getAddress_line2());
		address.setCity(addressAddDataApi.getCity());
		address.setState(addressAddDataApi.getState());
		address.setPinCode(addressAddDataApi.getPin_code());
		address.setCountry(addressAddDataApi.getCountry());
		address.setLatitude(addressAddDataApi.getLatitude());
		address.setLongitude(addressAddDataApi.getLongitude());
		address.setDefault(addressAddDataApi.is_default());
		address.setAddressType(addressAddDataApi.getAddress_type());
		
		return addressRepository.save(address);
		
	}
	
	public List<Address> getUserAddressDetailsList(int userId) {
		
	    Optional<List<Address>> addressList =	addressRepository.findByUserId(userId);
	    List<Address> addresses = addressList.get();
	    if (addressList.isEmpty()) {
	    	throw new AddressEmptyException(AddressConstants.EXCEPTION_ADDRESS_EMPTY);	
		}else {
			return addresses;
		}
		
	}
	
	public void deleteUserAddresses(int userId,int addressId) {
		
		Optional<Address> addressOptional= addressRepository.findByUserIdAndAddressId(userId, addressId);
		if (addressOptional.isEmpty()) {
			throw new AddressEmptyException(AddressConstants.EXCEPTION_ADDRESS_EMPTY);
		}else {
			addressRepository.deleteById(addressId);
		}
	}

}
