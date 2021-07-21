package com.usermanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.constants.AppConstants;
import com.usermanagement.exception.FileMissingException;
import com.usermanagement.exception.PasswordEncryptDecryptException;
import com.usermanagement.exception.SMTPException;
import com.usermanagement.properties.AppProperties;
import com.usermanagement.service.UserService;

@RestController
@RequestMapping("/forgotController")
public class ForgotPwdRestController {

	@Autowired
	private UserService userService;
	@Autowired
	private AppProperties appProp;
	
	@GetMapping("/forgotPwd/{email}")
	public String forgotPazzword(@PathVariable String email) throws SMTPException, FileMissingException, PasswordEncryptDecryptException {
		String msg=AppConstants.EMPTY_STRING;
		boolean frgtPwdFuctnlty = userService.frgtPwdFuctnlty(email);
		
		if(frgtPwdFuctnlty) {
			msg=appProp.getMessages().get(AppConstants.CHECK_EMAIL_FOR_PASSWORD);
			return msg;
		}else {
			msg=appProp.getMessages().get(AppConstants.INVALID_CRD);
			return msg;
		}
	}
}
