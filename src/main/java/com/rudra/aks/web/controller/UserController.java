package com.rudra.aks.web.controller;

import static com.rudra.aks.constants.Constants.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rudra.aks.model.UserBO;
import com.rudra.aks.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService		userService;
	
	/*
	 * Comment this method, only for calls test purpose.
	 */
	@RequestMapping("/")
	public String test() {
		logger.info("Controller accessed...");
		return "hellow security";
	}
	
	@RequestMapping( value="/addUser", method = RequestMethod.POST) 
	public String	addUser( @ModelAttribute("userform") UserBO user) {
		logger.info("Adding new user : " + user);
		boolean isUserCreated = false;
		if(user != null)
			isUserCreated = userService.saveUser(user);
		logger.info("User added : " + isUserCreated);
		if(!isUserCreated) 
			return "redirect:/" + INDEX_PAGE;
		return "redirect:/home";
	} 
	
	@RequestMapping(value="/registerForm")
	public String registrationForm() {
		logger.info("Registration form accessed...");
		return REGISTER_FORM;
	}
}
