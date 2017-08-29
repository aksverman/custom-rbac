package com.rudra.aks.service;

import java.util.Optional;

import com.rudra.aks.model.UserBO;

public interface UserService {

	Optional<UserBO> findUserByUserName(String username);
	
	boolean	saveUser(UserBO user);
	UserBO findUserById(int userid);
}
