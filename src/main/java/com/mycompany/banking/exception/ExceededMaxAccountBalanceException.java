package com.mycompany.banking.exception;

import java.text.NumberFormat;
import java.util.Locale;

public class ExceededMaxAccountBalanceException extends RuntimeException {

	private static final long serialVersionUID = 4342821241872841702L;

	public ExceededMaxAccountBalanceException(Double maxAccountBalance) {
		super(
				"An account cannot have a balance of more than " +
				NumberFormat.getCurrencyInstance(Locale.US).format(maxAccountBalance));
	}
}
