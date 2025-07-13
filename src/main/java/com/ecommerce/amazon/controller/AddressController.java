package com.ecommerce.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.amazon.constants.AddressConstants;
import com.ecommerce.amazon.entity.Address;
import com.ecommerce.amazon.payload.ApiResponse;
import com.ecommerce.amazon.pojo.AddressAddDataApi;
import com.ecommerce.amazon.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Address>> postMethodName(@RequestBody AddressAddDataApi addressAddDataApi) {
		
		Address address = addressService.addAddress(addressAddDataApi);
		
		ApiResponse<Address> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_ADDRESS_ADD,address);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@GetMapping("/{userId}/view")
	public ResponseEntity<ApiResponse<List<Address>>> getUserAddressList(@PathVariable int userId) {
		
        List<Address> address = addressService.getUserAddressDetailsList(userId);
		
		ApiResponse<List<Address>> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_ADDRESS_API,address);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@DeleteMapping("/{userId}/{addressId}/delete")
	public ResponseEntity<ApiResponse<String>> deleteAddressByUserId(@PathVariable int userId, @PathVariable int addressId) {
		
		addressService.deleteUserAddresses(userId, addressId);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,AddressConstants.SUCCESS_ADDRESS_API,AddressConstants.SUCCESS_ADDRESS_DELETE);
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	

}
