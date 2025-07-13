package com.ecommerce.amazon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.amazon.constants.AuthConstants;
import com.ecommerce.amazon.entity.User;
import com.ecommerce.amazon.enums.UserRole;
import com.ecommerce.amazon.exceptions.InvalidOtpForPasswordResetException;
import com.ecommerce.amazon.exceptions.InvalidPasswordException;
import com.ecommerce.amazon.exceptions.UserAlreadyExistException;
import com.ecommerce.amazon.exceptions.UserNotFound;
import com.ecommerce.amazon.pojo.ForgotPasswordSendOtp;
import com.ecommerce.amazon.pojo.LoginData;
import com.ecommerce.amazon.pojo.PasswordUpdateAfterReset;
import com.ecommerce.amazon.pojo.SignUpApiData;
import com.ecommerce.amazon.repository.UserRepository;
import com.ecommerce.amazon.utils.AuthUtility;

@Service
public class AuthService {
	
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private EmailService emailService;
	
	public Map<String, Object> signUpDataValidation(SignUpApiData signUpApiData) {
		
		Optional<User> dbOptional = userRepository.findByEmail(signUpApiData.getEmail());
		if (dbOptional.isPresent()) {	
			throw new UserAlreadyExistException(AuthConstants.ERROR_USER_ALREADY_EXISTS);	
		}
		
		User user=new User();
		user.setFirstName(signUpApiData.getFirstName());
		user.setLastName(signUpApiData.getLastName());
		user.setPasswordHash(passwordEncoder.encode(signUpApiData.getPasswordHash()));
		user.setPhoneNumber(signUpApiData.getPhoneNumber());
		user.setEmail(signUpApiData.getEmail());
		user.setRole(UserRole.BUYER);
		
		user= userRepository.save(user);
		
		String token=jwtService.generateJwtToken(user);
		Map<String, Object> response = new HashMap<>();
		
		response.put("userData", user);
		response.put("token", token);
		
		return response;
	}
	
	public Map<String, Object> loginDataValidation(LoginData loginData){
		Optional<User> dbUserOptional = userRepository.findByEmail(loginData.getEmail());
		if(dbUserOptional.isEmpty()) {
			throw new UserNotFound(AuthConstants.EXCEPTION_USER_NOT_FOUND);
		}
		User user = dbUserOptional.get();
		if(passwordEncoder.matches(loginData.getPassword(), user.getPasswordHash())==false) {
			throw new InvalidPasswordException(AuthConstants.INVALID_PASSWORD);
		}
		String token = jwtService.generateJwtToken(user);
		Map<String, Object> response = new HashMap<>();
		
		response.put("userData", user);
		response.put("token", token);
		
		return response;
	}
	
	public void forgotPassworSendOtp(ForgotPasswordSendOtp forgotPasswordSendOtp) {
		
		Optional<User> dbUserOptional = userRepository.findByEmail(forgotPasswordSendOtp.getEmail());
		if (dbUserOptional.isEmpty()) {
			throw new UserNotFound(AuthConstants.EXCEPTION_USER_NOT_FOUND);
		}
		User user= dbUserOptional.get();
		int otp = AuthUtility.generateOtp();
		emailService.sendOtpEmail(forgotPasswordSendOtp.getEmail(), Integer.toString(otp));
		user.setOtp(otp);
		userRepository.save(user);
	}
	
	public void passwordUpdateAfterReset(PasswordUpdateAfterReset passwordUpdateAfterReset) {
		Optional<User> dbUserOptional = userRepository.findByEmail(passwordUpdateAfterReset.getEmail());
		if (dbUserOptional.isEmpty()) {
			throw new UserNotFound(AuthConstants.EXCEPTION_USER_NOT_FOUND);
		}
		User user = dbUserOptional.get();
		
		if(Integer.parseInt(passwordUpdateAfterReset.getOtp())!=user.getOtp()) {
			throw new InvalidOtpForPasswordResetException(AuthConstants.EXCEPTION_INVALID_OTP_FOR_PASSWORD_RESET);
		}
		user.setPasswordHash(passwordEncoder.encode(passwordUpdateAfterReset.getPassword()));
		user.setOtp(0);
		
		userRepository.save(user);
		
	}

}
