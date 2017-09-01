package com.rudra.aks.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rudra.aks.model.Role;
import com.rudra.aks.service.impl.RoleServiceImpl;

import static com.rudra.aks.constants.Constants.*;


@Controller
@RequestMapping( "/role" )	
public class RoleController {

	@Autowired
	RoleServiceImpl		roleService;
	
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping("/roleform")
	public String getRoleForm() {
		return ROLE_FORM;
	}
	
	@RequestMapping("/addRole")
	public String		addRole(@ModelAttribute("roleform") Role role) {
		logger.info("Start : " + getClass().getName() + " : addRole()");
		boolean roleAdded = false;
		if ( role != null) 
			roleAdded = roleService.addRole(role);
		logger.info("Roles added : " + roleAdded);
		if(roleAdded)
			return "redirect:/home";
		return "redirect:/index";
	}
}
