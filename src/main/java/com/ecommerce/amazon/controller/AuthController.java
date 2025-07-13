package com.ecommerce.amazon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.amazon.constants.AuthConstants;
import com.ecommerce.amazon.entity.User;
import com.ecommerce.amazon.payload.ApiResponse;
import com.ecommerce.amazon.pojo.ForgotPasswordSendOtp;
import com.ecommerce.amazon.pojo.LoginData;
import com.ecommerce.amazon.pojo.PasswordUpdateAfterReset;
import com.ecommerce.amazon.pojo.SignUpApiData;
import com.ecommerce.amazon.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody SignUpApiData signUpApiData) {
		
		Map<String, Object> newUser = authService.signUpDataValidation(signUpApiData);
		
		ApiResponse<User> apiResponse = new ApiResponse<>(true,AuthConstants.SUCCESS_ACCOUNT_CREATED_MSG,(User) newUser.get("userData"));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("Authorization","Bearer "+newUser.get("token").toString());
		
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody LoginData loginData) {
		
		Map<String, Object> userResponse = authService.loginDataValidation(loginData);
		
		ApiResponse<User> apiResponse = new ApiResponse<>(true,AuthConstants.LOGIN_SUCCESS,(User) userResponse.get("userData"));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("Authorization","Bearer "+userResponse.get("token").toString());
		
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiResponse);
		
	}
	
	@PostMapping("/forgot-passowrd/send-otp")
	public ResponseEntity<ApiResponse<String>> postMethodName(@Valid @RequestBody ForgotPasswordSendOtp forgotPasswordSendOtp) {
		authService.forgotPassworSendOtp(forgotPasswordSendOtp);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,AuthConstants.SUCCESS_OTP_SENT_TO_EMAIL,"");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	
	@PostMapping("/forgot-passowrd/update-password")
	public ResponseEntity<ApiResponse<String>> updatePasswordAfterReset(@Valid @RequestBody PasswordUpdateAfterReset passwordUpdateAfterReset){
		authService.passwordUpdateAfterReset(passwordUpdateAfterReset);
		
		ApiResponse<String> apiResponse = new ApiResponse<>(true,AuthConstants.PASSWORD_UPDATED_SUCCESS,"");
		
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
