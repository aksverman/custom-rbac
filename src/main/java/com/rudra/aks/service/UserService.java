package com.rudra.aks.service;

import java.util.List;
import java.util.Optional;

import com.rudra.aks.exception.CustomException;
import com.rudra.aks.model.UserBO;

public interface UserService {

	Optional<UserBO> findUserByUserName(String username);
	
	UserBO findUserById(int userid);

	List<UserBO>	usersList();
	
	boolean	saveUser(UserBO user);
	
	UserBO	updateUser(UserBO user) throws CustomException;
	
	boolean deleteUser(String username) throws Exception;
	
	List<String>	getUpdatableUsersList();

	boolean resetPassword(String username, String contextPath);

	String validatePasswordChangeRequest(int userid, String token);

	String updatePassword(int userid, String pass1);

}
