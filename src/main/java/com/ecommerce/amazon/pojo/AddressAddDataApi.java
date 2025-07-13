package com.ecommerce.amazon.pojo;

import com.ecommerce.amazon.enums.AddressType;

import lombok.Data;

@Data
public class AddressAddDataApi {
	
	private int user_id;
	
	private String address_line1;
	
	private String address_line2;
	
	private String city;
	
	private String state;
	
	private String pin_code;
	
	private String country;
	
	private double latitude;
	
	private double longitude;
	
	private boolean is_default;
	
	private AddressType address_type;
	
	
	
	/*
	 * SELECT `addresses`.`address_id`,
    `addresses`.`user_id`,
    `addresses`.`address_line1`,
    `addresses`.`address_line2`,
    `addresses`.`city`,
    `addresses`.`state`,
    `addresses`.`pin_code`,
    `addresses`.`country`,
    `addresses`.`latitude`,
    `addresses`.`longitude`,
    `addresses`.`is_default`,
    `addresses`.`address_type`,
    `addresses`.`created_on`,
    `addresses`.`updated_on`
	FROM `amazon_db`.`addresses`;
	 * 
	 */

}
