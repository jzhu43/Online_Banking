/*This is the checkbook request controller. 
 * When the user gets directed to "/requestCheckBook", the requestCheckBook function 
 * returns checkbookreq.html. 
 * In the function requestCheckBookProcess, the user is able to submit their request to
 * the database and it gets stored into the checkbook_reqs table.
 */
package com.mycompany.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.banking.model.CheckbookRequest;
import com.mycompany.banking.service.CheckBookReqService;
import com.mycompany.banking.service.UserService;

@Controller
public class CheckBookRequestController {

	@Autowired
	CheckBookReqService checkBookReqService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/requestCheckBook")
	public String requestCheckBook(@SessionAttribute(name="userId", required=false) Long userId, Model model) {
		
		if (userId == null) {
			return "redirect:index";
		}
		
		model.addAttribute("checkBookReq", new CheckbookRequest());
		
		return "checkbookreq";
	}
	
	@PostMapping(value="/requestCheckBook")
	public String requestCheckBookProcess(@SessionAttribute("userId") Long userId,
			@ModelAttribute("checkBookReq") CheckbookRequest request, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "checkbookreq";
		}
		request.setUserId(userId);
		checkBookReqService.submitCheckBookRequest(request);
		
		return "redirect:ICINBank";
	}
	
}
