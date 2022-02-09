package com.mycompany.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Exceptions {

	@ResponseBody
	@ExceptionHandler(NotEnoughMoneyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String notEnoughMoneyHandler(NotEnoughMoneyException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(UserDisabledException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String userDisabledHandler(UserDisabledException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(ExceededWithdrawalLimitException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String exceededWithdrawalLimitHandler(ExceededWithdrawalLimitException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(AccountDoesNotExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String accountDoesNotExistHandler(AccountDoesNotExistException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(NegativeAmountException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String negativeAmountHandler(NegativeAmountException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(ExceededMaxAccountBalanceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String exceededMaxAccountBalanceHandler(ExceededMaxAccountBalanceException exception) {
		return exception.getMessage();
	}
}