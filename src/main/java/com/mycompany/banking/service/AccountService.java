package com.mycompany.banking.service;

import com.mycompany.banking.model.Account;

public interface AccountService {

	public Account getAccount(Long id);
	
	public void processWithdrawal(Long id, Double amount);
	
	public void processDeposit(Long id, Double amount);
	
}
