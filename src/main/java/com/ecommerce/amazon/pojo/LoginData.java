package com.ecommerce.amazon.pojo;

import com.ecommerce.amazon.constants.AuthConstants;
import com.ecommerce.amazon.constants.GenericConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginData {
	
	@NotNull(message = AuthConstants.EMAIL_IS_REQUIRED)
	@Pattern(regexp = GenericConstants.EMAIL_FORMAT,message = AuthConstants.INVALID_EMAIL_FORMAT)
	private String email;
	
	@NotNull(message = AuthConstants.PASSWORD_IS_REQUIRED)
	@Size(min = 8,message = AuthConstants.PASSWORD_NAME_MUST_BE_REQUIERED_RANGE)
	private String password;

}
