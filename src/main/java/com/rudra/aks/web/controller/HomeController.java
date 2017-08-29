package com.rudra.aks.web.controller;

import static com.rudra.aks.constants.Constants.INDEX_PAGE;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView	  homeView() {
		logger.info("HomeController : index page accessed");
		return new ModelAndView(INDEX_PAGE);
	}
}
