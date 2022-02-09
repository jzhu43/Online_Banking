/* This is the sign in controller.
 * We have a session attribute called userId that keeps track of the current user that is logged in.
 * The get mapping signin function returns the signin.html page.
 * The post mapping signinProcess function validates the login information through
 * the database and returns the user. If the user is null, they will not be able to proceed, 
 * if not null, meaning they found the user in the database with the correct credentials, 
 * they will be redirected to the user portal. There is also a logout method that is used to close
 * the session attribute and redirect back to the signin page.
 */
package com.mycompany.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.banking.model.Login;
import com.mycompany.banking.model.User;
import com.mycompany.banking.service.UserService;



@Controller
@SessionAttributes("userId")
public class SignInController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("userId")
	public Long userId() {
		return null;
	}
	
	@GetMapping("/index")
	public String signin(Model model) {
		model.addAttribute("login", new Login());
		return "signin";
	}
	
	@PostMapping("/index")
	public String signinProcess(@ModelAttribute("login") Login login, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "signin";
		}
		User user = userService.validateUser(login);
		
		if(user != null) {
			model.addAttribute("userId", user.getUserId());
			return "redirect:ICINBank";
		}
		redirectAttributes.addFlashAttribute("error", "Incorrect credentials!");
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/index";
	}
	
}
