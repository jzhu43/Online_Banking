/*This account controller is responsible for fetching the current session user's
 * balances for primary and savings. The two HTML pages will be  displayed when 
 * you press view details on primary or savings on the user portal or by directing to
 * "/primary" or "/savings".
 */
package com.mycompany.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.banking.model.Account;
import com.mycompany.banking.model.User;
import com.mycompany.banking.service.AccountService;
import com.mycompany.banking.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;

	@GetMapping("/primary")
	String primaryDetails(@SessionAttribute(name="userId", required=false) Long userId, Model model) {
		
		if (userId == null) {
			return "redirect:index";
		}
		
		User user = userService.getUser(userId);
		Account primaryAccount = accountService.getAccount(user.getPrimary().getAccountId());
		model.addAttribute("account", primaryAccount);
		return "useraccountdetails";
	}
	
	@GetMapping("/savings")
	String savingsDetails(@SessionAttribute(name="userId", required=false) Long userId, Model model) {
		
		if (userId == null) {
			return "redirect:index";
		}
		
		User user = userService.getUser(userId);
		Account savingsAccount = accountService.getAccount(user.getSavings().getAccountId());
		model.addAttribute("account", savingsAccount);
		return "useraccountdetails";
	}
}
