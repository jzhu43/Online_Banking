package com.mycompany.banking.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.banking.service.UserService;

@Component
public class NotDuplicateEmailValidator implements ConstraintValidator<NotDuplicateEmail, String>{
	
	@Autowired
	private UserService userService;
	
	public void initialize(NotDuplicateEmail notExistingEmail){
		
	}
	
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !userService.doesEmailExist(email);

	}
	
}

