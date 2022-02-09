package com.mycompany.banking.exception;

public class UserDisabledException extends RuntimeException {

	private static final long serialVersionUID = -3383331772853109586L;

	public UserDisabledException() {
		super("Your account has been disabled");
	}
}
