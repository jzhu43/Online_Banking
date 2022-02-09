package com.mycompany.banking.exception;

public class NegativeAmountException extends RuntimeException {

	private static final long serialVersionUID = -3581331133439596227L;
	
	public NegativeAmountException() {
		super("The amount of money for a transaction cannot be negative");
	}
}
