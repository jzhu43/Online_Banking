package com.mycompany.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.banking.exception.AccountDoesNotExistException;
import com.mycompany.banking.exception.ExceededMaxAccountBalanceException;
import com.mycompany.banking.exception.ExceededWithdrawalLimitException;
import com.mycompany.banking.exception.NegativeAmountException;
import com.mycompany.banking.exception.NotEnoughMoneyException;
import com.mycompany.banking.model.Account;
import com.mycompany.banking.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Double MAX_WITHDRAWAL_AMOUNT = 10000.0;
	private static final Double MAX_ACCOUNT_BALANCE = 1000000000000.0;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account getAccount(Long id) {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			return account.get();
		}
		return null;
	}

	@Override
	public void processWithdrawal(Long id, Double amount) {
		Account account = getAccount(id);
		
		if (account == null) {
			throw new AccountDoesNotExistException(id);
		} else if (amount < 0) {
			throw new NegativeAmountException();
		} else if (amount > MAX_WITHDRAWAL_AMOUNT) {
			throw new ExceededWithdrawalLimitException(MAX_WITHDRAWAL_AMOUNT);
		} else if (amount > account.getBalance()) {
			throw new NotEnoughMoneyException();
		} else {
			account.setBalance(account.getBalance() - amount);
			accountRepository.save(account);
		}
	}

	@Override
	public void processDeposit(Long id, Double amount) {
		Account account = getAccount(id);
		
		if (account == null) {
			throw new AccountDoesNotExistException(id);
		} else if (amount < 0){
			throw new NegativeAmountException();
		} else if (account.getBalance() + amount > MAX_ACCOUNT_BALANCE) {
			throw new ExceededMaxAccountBalanceException(MAX_ACCOUNT_BALANCE);
		} else {
			account.setBalance(account.getBalance() + amount);
			accountRepository.save(account);
		}
	}
	
}
