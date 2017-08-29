package com.rudra.aks.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rudra.aks.model.CurrentUser;
import com.rudra.aks.model.UserBO;
import com.rudra.aks.service.UserService;


@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	@Autowired
	UserService		userService;
	
	private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Access user info from db");
		UserBO user = userService.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));
        return new CurrentUser(user);
	}

}
