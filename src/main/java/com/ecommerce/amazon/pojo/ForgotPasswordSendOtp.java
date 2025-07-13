package com.ecommerce.amazon.pojo;

import com.ecommerce.amazon.constants.AuthConstants;
import com.ecommerce.amazon.constants.GenericConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotPasswordSendOtp {
	
	@NotNull(message = AuthConstants.EMAIL_IS_REQUIRED)
	@Pattern(regexp = GenericConstants.EMAIL_FORMAT,message = AuthConstants.INVALID_EMAIL_FORMAT)
	private String email;

}
