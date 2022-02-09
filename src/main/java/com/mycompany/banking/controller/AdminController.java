/*This controller is responsible for all the functionality of the admin side.
 * This class starts off with having a session attribute of "validAdmin" which keeps track of
 * the admin session when logged in. The adminLogin function will return adminlogin.html.
 * The adminLoginProcess will validate if admin credentials are valid. If so, it will redirect to the adminportal.
 * The adminPortal function will return the adminportal.html after checking if there is a valid admin session.
 * The getUserAccounts function will retrieve all the accounts from the database and display it in useraccounts.html.
 * The getAllCheckbooks function will retrieve all the checkbook requests from the database and display it in the allcheckbooks.html.
 * The enableUser function will enable/disable specific users from being able to deposit/withdraw/transfer.
 * The confirmCheckbookReq function will be for the admin to be able to "confirm" a checkbook and update the database.
 * The userAccountDetails function retrieves information for a user account and returns a view that displays the account details.
 */
package com.mycompany.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.banking.model.Account;
import com.mycompany.banking.model.Admin;
import com.mycompany.banking.model.CheckbookRequest;
import com.mycompany.banking.model.User;
import com.mycompany.banking.model.UserCheckbookReq;
import com.mycompany.banking.service.AccountService;
import com.mycompany.banking.service.AdminService;
import com.mycompany.banking.service.CheckBookReqService;
import com.mycompany.banking.service.UserService;


@Controller
@RequestMapping("/admin")
@SessionAttributes("validAdmin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CheckBookReqService checkbookReqService;
	
	@ModelAttribute
	public Boolean validAdmin() {
		return false;
	}
	
	@GetMapping("/login")
	public String adminLogin(Model model) {
		model.addAttribute("admin", new Admin());
		return "adminlogin";
	}
	
	@PostMapping("/login")
	public String adminLoginProcess(@ModelAttribute("admin") Admin admin, Model model) {
		
		boolean validAdmin = adminService.validateAdmin(admin);
		if(validAdmin) {
			model.addAttribute("validAdmin", true);
			return "redirect:portal";
		}
		return "adminlogin";
	}
	
	@GetMapping("/logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();
		return "redirect:login";
	}
	
	@GetMapping("/portal")
	public String adminPortal(
			@SessionAttribute(name="validAdmin", required=false)
			Boolean validAdmin) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:login";
		}
		return "adminportal";
	}
		
	@GetMapping("/userAccount")
	public String getUserAccounts(
			@SessionAttribute(name="validAdmin", required=false)
			Boolean validAdmin,
			Model model) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:login";
		}
		
		List<User> userList = userService.getAllUsers();
		model.addAttribute("users", userList);
		return "useraccounts";
	}
	
	@GetMapping("/check")
	public String getAllCheckbooks(
			@SessionAttribute(name="validAdmin", required=false)
			Boolean validAdmin,
			Model model) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:login";
		}
		
		List<UserCheckbookReq> userCheckbookReqList = checkbookReqService.getAllUserCheckbookReqs();
		model.addAttribute("userCheckbookReqList", userCheckbookReqList);
		return "allcheckbooks";
	}
	
	@GetMapping("/{action}/{userId}")
	public String enableUser(
			@PathVariable("action") String action,
			@PathVariable("userId") Long userId,
			@SessionAttribute(name="validAdmin", required=false) Boolean validAdmin) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:/admin/login";
		}
		
		User user = userService.getUser(userId);
		
		if (action.equals("enable")) {
			user.setEnabled(true);
		} else {
			user.setEnabled(false);
		}
		
		userService.updateUser(user);
		return "redirect:/admin/userAccount";
	}
	
	@GetMapping("/confirm/{requestNumber}")
	public String confirmCheckbookReq(
			@PathVariable("requestNumber") Long requestNumber,
			@SessionAttribute(name="validAdmin", required=false) Boolean validAdmin) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:/admin/login";
		}
		
		CheckbookRequest request = checkbookReqService.getCheckbookReq(requestNumber);
		request.setConfirmed(true);
		checkbookReqService.updateCheckbookReq(request);

		return "redirect:/admin/check";
	}
	
	@GetMapping("/userAccount/{accountId}")
	String userAccountDetails(
			@PathVariable("accountId") Long accountId,
			@SessionAttribute(name="validAdmin", required=false) Boolean validAdmin, 
			Model model) {
		
		if (validAdmin == null || validAdmin == false) {
			return "redirect:/admin/login";
		}
		
		Account account = accountService.getAccount(accountId);
		model.addAttribute("account", account);
		
		return "adminaccountdetails";
	}
	
}
