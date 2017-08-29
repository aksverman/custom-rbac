package com.rudra.aks.service;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudra.aks.model.Role;
import com.rudra.aks.model.UserBO;
import com.rudra.aks.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository	userRepository;
	
	public Optional<UserBO> findUserByUserName(String username) {
		logger.info("Start: " + getClass().getName() + " : findUserByUserName");
		return userRepository.findOneByUsername(username);
	}

	@Override
	public boolean saveUser(UserBO user) {
		UserBO	userbo = null;
		if( user.getRoles() == null )
			addDefaultRole(user);
		userbo = userRepository.save(user);
		logger.info("User saved : " + user);
		return userbo != null;
	}

	private void addDefaultRole(UserBO user) {
		logger.info("Adding default roles to user : " + user.getUsername());
		Role role = new Role();
		role.setName("ROLE_USER");
	}

	@Override
	public UserBO findUserById(int userid) {
		UserBO userBO = userRepository.findOne(userid);
		return userBO;
	}
	
	
}
