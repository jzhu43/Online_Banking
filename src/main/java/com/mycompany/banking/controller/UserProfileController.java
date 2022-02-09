/* This is the user profile controller. It is responsible for directing to the specific user
 * session's profile. the userProfile function checks if there is a valid user session, then fetches
 * for the user using userService and returns the userprofile.html, which displays all the user information.
 * The user is able to change their information such as name, password, email, etc. Once the user is done changing
 * and presses the edit button, the post mapping request will check the updatedUser information with @Valid annotation
 * and updates the user into the database.
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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.banking.model.User;
import com.mycompany.banking.service.UserService;

@Controller
public class UserProfileController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/user/profile")
	public String userProfile(@SessionAttribute(name="userId", required=false) Long userId, Model model) {
		
		if (userId == null) {
			return "redirect:/index";
		}
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "userprofile";
	}
	
	@PostMapping(value="/update_profile")
	public String userProfile(@SessionAttribute(name="userId", required=false) Long userId,
			@Valid @ModelAttribute("user") User updatedUser,
			BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			User user = userService.getUser(userId);
			updatedUser.setPrimary(user.getPrimary());
			updatedUser.setSavings(user.getSavings());
			userService.updateUser(updatedUser);
		}

		return "redirect:/user/profile";
	}
}
