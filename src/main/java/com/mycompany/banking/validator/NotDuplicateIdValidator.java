package com.mycompany.banking.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.banking.service.UserService;

@Component
public class NotDuplicateIdValidator implements ConstraintValidator<NotDuplicateId, Long>{
	
	@Autowired
	private UserService userService;
	
	public void initialize(NotDuplicateId notExistingId){
		
	}
	

	public boolean isValid(Long id, ConstraintValidatorContext context) {
		return !userService.doesIdExist(id);

	}
	
}
