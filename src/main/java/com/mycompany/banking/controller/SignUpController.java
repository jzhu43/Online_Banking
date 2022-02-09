/*This is a sign up controller. There are two methods in this class, one for get requests and one
 * for the post request. Whenever the application redirects to /signup, a new user model is created
 * and the signup.html page will be returned. The page will prompt to be filled completely and validation
 * checks are in place by using @Valid annotation. The signupProcess method checks this validity and then
 * registers the user into the database with the given user information.
 */
package com.mycompany.banking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.banking.model.User;
import com.mycompany.banking.service.UserService;

@Controller
public class SignUpController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@PostMapping(value="/signup")
	public String signupProcess(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "signup";
		}

		if (!userService.register(user)) {
			redirectAttributes.addFlashAttribute("error", "Sign up failed");
		} else {
			redirectAttributes.addFlashAttribute("success", "Sign up was successful");
		}
		return "redirect:/signup";
	}
	
}
