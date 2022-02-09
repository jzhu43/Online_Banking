/* This controller is responsible for the deposit, withdraw, and transfer
 * functionality. Those requests check for valid user session
 * and then return the correct page.
 * In the deposit post request, depositProcess, the user gets checked if they 
 * are enabled to deposit and then adds the deposit to their primary or savings in the database.
 * In the withdraw post request, processWithdrawl, the user also gets checked if they are enabled
 * to withdraw. If they are enabled, the balance of the selected account will also be checked whether or not they are 
 * withdrawing more than they have. If withdrawing more than the account balance, There will be an error message.
 * Otherwise, the withdrawal will be completed.
 */
package com.mycompany.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.banking.exception.UserDisabledException;
import com.mycompany.banking.model.Deposit;
import com.mycompany.banking.model.Transfer;
import com.mycompany.banking.model.User;
import com.mycompany.banking.model.Withdraw;
import com.mycompany.banking.repository.AccountRepository;
import com.mycompany.banking.service.AccountService;
import com.mycompany.banking.service.UserService;

@Controller
public class TransactionController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
  
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/withdraw")
	public String withdraw(
			@SessionAttribute(name="userId", required=false) Long userId,
			Model model) {
		
		if (userId == null) {
			return "redirect:index";
		}
		
		model.addAttribute("withdrawInfo", new Withdraw());
		return "withdraw";
	}

	@PostMapping("/withdraw")
	public String processWithdrawal(
			@SessionAttribute(name="userId") Long userId,
			@ModelAttribute("withdrawInfo") Withdraw withdrawInfo,
			RedirectAttributes redirectAttributes) {

		User user = userService.getUser(userId);
		
		try {
			if (user.getEnabled() == false) {
				throw new UserDisabledException();
				
			} else if (withdrawInfo.getAccountType().equals("primary")) {
				accountService.processWithdrawal(user.getPrimary().getAccountId(), 
						withdrawInfo.getAmount());
			} else {
				accountService.processWithdrawal(user.getSavings().getAccountId(),
						withdrawInfo.getAmount());
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Transaction Failed: " + e.getMessage());
			return "redirect:withdraw";
		}
		
		return "redirect:ICINBank";
	}
	
	@GetMapping("/deposit")
	public String deposit(
			@SessionAttribute(name="userId", required=false) Long userId,
			Model model) {
		
		if (userId == null) {
			return "redirect:index";
		}
		
		model.addAttribute("deposit", new Deposit());
		return "deposit";
	}
	
	@PostMapping("/deposit")
	public String depositProcess(
			@SessionAttribute(name="userId") Long userId,
			@ModelAttribute("deposit") Deposit dep,
			RedirectAttributes redirectAttributes) {

		User user = userService.getUser(userId);
		
		try {
			if (user.getEnabled() == false) {
				throw new UserDisabledException();
				
			} else if (dep.getAccountType().equals("primary")) {			
				accountService.processDeposit(user.getPrimary().getAccountId(), dep.getAmount());		
			} else {
				accountService.processDeposit(user.getSavings().getAccountId(), dep.getAmount());
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Transaction Failed: " + e.getMessage());
			return "redirect:deposit";
		}
		
		return "redirect:ICINBank";
	}
	
	@GetMapping("/transfer")
	public String transfer(
			@SessionAttribute(name="userId", required = false) Long userId,
			@SessionAttribute(name="enabled", required=false) Boolean enabled,
			Model model) {
		
		if(userId == null) {
			return "redirect:index";
		}
		
		model.addAttribute("transfer", new Transfer());
		return "transfer";
	}
	
	@PostMapping("/transfer")
	public String transferProcess(
			@SessionAttribute("userId") Long userId,
			@ModelAttribute("transfer") Transfer transferInfo,
			RedirectAttributes redirectAttributes) {
		
		User user = userService.getUser(userId);
		try {
			if (user.getEnabled() == false) {
				throw new UserDisabledException();
				
			} else if (transferInfo.getAccountType().equals("primary")) {
				accountService.processWithdrawal(user.getPrimary().getAccountId(), transferInfo.getAmount());
				accountService.processDeposit(transferInfo.getId(), transferInfo.getAmount());
			} else {
				accountService.processWithdrawal(user.getSavings().getAccountId(), transferInfo.getAmount());
				accountService.processDeposit(transferInfo.getId(), transferInfo.getAmount());
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Transaction Failed: " + e.getMessage());
			return "redirect:transfer";
		}
		
		return "redirect:ICINBank";
	}

}
