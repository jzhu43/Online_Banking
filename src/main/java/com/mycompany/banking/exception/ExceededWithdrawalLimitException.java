package com.mycompany.banking.exception;

import java.text.NumberFormat;
import java.util.Locale;

public class ExceededWithdrawalLimitException extends RuntimeException{

	private static final long serialVersionUID = -7257876741220361175L;

	public ExceededWithdrawalLimitException(Double maxWithdrawalAmount) {
		super(
				"You cannot withdraw more than " +
				NumberFormat.getCurrencyInstance(Locale.US).format(maxWithdrawalAmount) +
				" at a time");
	}
}
