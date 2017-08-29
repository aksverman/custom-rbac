package com.rudra.aks.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rudra.aks.constants.Constants.*;

@Controller
@RequestMapping("/accessdenied")
public class AccessDeniedController {

	private static Logger logger = Logger.getLogger(AccessDeniedController.class);
	
	@RequestMapping("/")
	public String accessDeniedPage() {
		logger.info("Access Denied controller");
		return ACCESSDENIED_PAGE;
	}
}
