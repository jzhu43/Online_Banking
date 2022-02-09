package com.mycompany.banking.exception;

public class NotEnoughMoneyException extends RuntimeException {

	private static final long serialVersionUID = 4865557279929549662L;

	public NotEnoughMoneyException() {
		super("Your balance is too low to withdraw the requested amount");
	}
}
