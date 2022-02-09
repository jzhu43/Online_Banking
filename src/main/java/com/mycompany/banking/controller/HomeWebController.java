/* This controller is specifically for the user portal, once signed in as a specific user.
 * In the user portal, ICINBank, it is responsible for fetching information about the user.
 * The balances for both primary and savings account should be shown, as well as options to
 * deposit and withdraw. There is also a quick get request function that redirects to the signin
 * page if the user goes to the "/" mapping.
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
public class HomeWebController {
  
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public String redirect() {
		return "redirect:index";
	}
	
	@GetMapping("/ICINBank")
	public String ICINBank(@SessionAttribute(name="userId", required=false) Long userId, Model model) {
		
		if(userId == null) {
			return "redirect:index";
		}
        User user = userService.getUser(userId);

        Account primaryAccount = accountService.getAccount(user.getPrimary().getAccountId());
        Account savingsAccount = accountService.getAccount(user.getSavings().getAccountId());
        System.out.println(primaryAccount.getBalance());

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "userportal";
    }
}
