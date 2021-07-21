package com.usermanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.constants.AppConstants;
import com.usermanagement.exception.PasswordEncryptDecryptException;
import com.usermanagement.formbinding.UnlockAccForm;
import com.usermanagement.service.UserService;

@RestController
@RequestMapping("/unlockController")
public class UnlockAccRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/unlockAcc")
	public String unlockAccount(@RequestBody UnlockAccForm unloclAccForm) throws PasswordEncryptDecryptException {
		String msg=AppConstants.EMPTY_STRING;
		boolean fnctnlty = userService.unlckAccFnctnlty(unloclAccForm);
		if(fnctnlty) {
			msg=AppConstants.UNLOCK_SUCCESS;
			return msg;
		}else {
			msg=AppConstants.INVALID_CRD;
			return msg;
		}
	}
}
