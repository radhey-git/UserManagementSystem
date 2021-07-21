package com.usermanagement.service;

import java.util.Map;

import com.usermanagement.exception.FileMissingException;
import com.usermanagement.exception.PasswordEncryptDecryptException;
import com.usermanagement.exception.SMTPException;
import com.usermanagement.formbinding.LoginForm;
import com.usermanagement.formbinding.RegForm;
import com.usermanagement.formbinding.UnlockAccForm;

public interface UserService {

	
	public Map<Integer, String> getCountry();
	
	public Map<Integer, String> getState(Integer countryId);
	
	public Map<Integer, String> getCity(Integer stateId);
	
	public String handleSignInBtn(LoginForm loginForm) throws PasswordEncryptDecryptException; //user is lock or unlock or check credential due to 
														//all these checking need String return type
	
	public String checkEmail(String email); //valid email checking
	
	public boolean hndlSignUpBtn(RegForm regForm) throws SMTPException, FileMissingException, PasswordEncryptDecryptException; //save New User
	
	public boolean unlckAccFnctnlty(UnlockAccForm unloclAccForm) throws PasswordEncryptDecryptException;
	
	public boolean frgtPwdFuctnlty(String email) throws SMTPException, FileMissingException, PasswordEncryptDecryptException;
	
}
