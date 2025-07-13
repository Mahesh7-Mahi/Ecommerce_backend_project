package com.ecommerce.amazon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ecommerce.amazon.enums.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	
	private int userId;
	
	private String addressLine1;
	
	
	private String addressLine2;
	
	private String city;
	
	private String state;
	
	private String pinCode;
	
	private String country;
	
	private double latitude;
	
	private double longitude;
	
	private boolean isDefault;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	private LocalDateTime createdOn = LocalDateTime.now();
	
	private LocalDateTime updatedOn = LocalDateTime.now();
	

}
