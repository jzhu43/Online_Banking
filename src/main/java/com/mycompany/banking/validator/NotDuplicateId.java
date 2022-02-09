package com.mycompany.banking.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDuplicateIdValidator.class)
@Documented
public @interface NotDuplicateId{
	String message() default "Another user with the same UserID has already been registered";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}


