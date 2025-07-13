package com.ecommerce.amazon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ecommerce.amazon.constants.GenericConstants;
import com.ecommerce.amazon.exceptions.EmailSendingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Value("${spring.mail.username}")
	public String fromEmail;
	
	public void sendOtpEmail(String toEmail,String otpCode) {
		
		Context context = new Context();
		
		context.setVariable("otp", otpCode);
		
		String htmlContent = templateEngine.process("otpemail.html", context);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
			helper.setText(htmlContent,true);
			helper.setTo(toEmail);
			helper.setSubject("Your Amazon OTP to Reset Your Password");
			helper.setFrom(fromEmail);
			mailSender.send(mimeMessage);
		}catch (MessagingException e) {
			throw new EmailSendingException(GenericConstants.UNABLE_TO_SEND_EMAIL);
		}
		
	}

}
