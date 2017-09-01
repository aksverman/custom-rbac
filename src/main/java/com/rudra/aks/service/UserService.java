package com.rudra.aks.service;

import java.util.List;
import java.util.Optional;

import com.rudra.aks.model.UserBO;

public interface UserService {

	Optional<UserBO> findUserByUserName(String username);
	
	UserBO findUserById(int userid);

	List<UserBO>	usersList();
	
	boolean	saveUser(UserBO user);
	
	UserBO	updateUser(UserBO user);
	
	boolean deleteUser(String username);
	
	List<String>	getUpdatableUsersList();
}
