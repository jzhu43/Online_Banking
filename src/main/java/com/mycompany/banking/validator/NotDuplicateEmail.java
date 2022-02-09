package com.mycompany.banking.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotDuplicateEmailValidator.class)
@Documented
public @interface NotDuplicateEmail {
	String message() default "Another user with this email has already been registered";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
