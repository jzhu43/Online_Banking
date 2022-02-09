package com.mycompany.banking.exception;


public class AccountDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1832344801943134969L;

	public AccountDoesNotExistException(Long id) {
		super("The account " + id + " does not exist");
	}
}
