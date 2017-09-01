package com.rudra.aks.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rudra.aks.model.Role;
import com.rudra.aks.model.UserBO;
import com.rudra.aks.repository.RoleRepository;
import com.rudra.aks.repository.UserRepository;
import com.rudra.aks.service.RoleService;
import com.rudra.aks.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository	userRepository;
	
	@Autowired
	RoleRepository	roleRepository;
	
	@Autowired
	RoleService		roleService;
	
	@Autowired
	EntityManagerFactory	emFactory;
	
	private EntityManager entityManager;
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = emFactory.createEntityManager();
	}
	
	public Optional<UserBO> findUserByUserName(String username) {
		logger.info("Start: " + getClass().getName() + " : findUserByUserName");
		return userRepository.findOneByUsername(username);
	}

	@Override
	public boolean saveUser(UserBO user) {
		UserBO	userbo = null;
		if( user.getRole() == null )
			addDefaultRole(user);
		addSelectedRoleToUser(user);
		userbo = userRepository.save(user);
		logger.info("User saved : " + user);
		return userbo != null;
	}


	@Override
	public UserBO findUserById(int userid) {
		UserBO userBO = userRepository.findOne(userid);
		return userBO;
	}

	@Override
	public boolean deleteUser(String username) {
		userRepository.delete(userRepository.findOneByUsername(username).get());
		return true;
	}
	
	@Override
	public List<UserBO> usersList() {
		return userRepository.findAll();
	}

	/*
	 * To Update an existing user.
	 * Get existing user' userid with proxy and 
	 * set expected user's details to proxy and update user with session.
	 * 
	 * @see com.rudra.aks.service.UserService#updateUser(com.rudra.aks.model.UserBO)
	 */
	@Override
	public UserBO updateUser(UserBO user) {
		//return userRepository.save(user);
		//user.setUserid(userRepository.findOneByUsername(user.getUsername()).get().getUserid());
		logger.info("Start : " + getClass().getName() + " : updateUser()");
		logger.info("User to update : " + user);
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		
		UserBO userfromdb = userRepository.findOneByUsername(user.getUsername()).get();
		int userid = userfromdb.getUserid();
		Role existingRole = userfromdb.getRole();
		
		UserBO userToUpdate = userRepository.getOne(userid);
		userToUpdate.setEmailid(user.getEmailid());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setRole(existingRole);
		
		session.update(userToUpdate);
		tx.commit();
		session.close();
		
		return userToUpdate;
	}
	
	/**
	 * Get list of users' id which are updatable
	 */
	/*@Override
	public List<Integer> getUpdatableUsersList() {
		List<UserBO> allUsers = userRepository.findAll();
		List<Integer>	userIds;
		if ( !allUsers.isEmpty()) {
			userIds = new ArrayList<Integer>( allUsers.size() );
			for ( UserBO user : allUsers )
				userIds.add(user.getUserid());
			return userIds;
		}
		return null;
	}*/
	
	/**
	 * Users name to update
	 */
	@Override
	public List<String> getUpdatableUsersList() {
		List<UserBO> allUsers = userRepository.findAll();
		List<String>	usernames;
		if ( !allUsers.isEmpty()) {
			usernames = new ArrayList<String>( allUsers.size() );
			for ( UserBO user : allUsers )
				usernames.add(user.getUsername());
			return usernames;
		}
		return null;
	}
	
	/**
	 * Set default role to new user, DEV is default role.
	 * @param user
	 */
	private void addDefaultRole(UserBO user) {
		logger.info("Adding default roles to user : " + user.getUsername());
		//Collection<Role> roles = Arrays.asList( roleService.findRoleById(1) );
		Role roleToAssign = roleService.findRoleById(1);
		user.setRole(roleToAssign);
	}

	/**
	 * Set requested role to user, bcoz, ROLEs cannot be added.
	 * It has to update the reference only.
	 * @param user
	 */
	private void addSelectedRoleToUser(UserBO user) {
		logger.info("Assigning role selected by user : " + user.getRole());
		Role  role = roleService.findRoleWithRoleName(user.getRole().getName()).get();
		user.setRole(role);
		logger.info("User's role added : " + user);
	}

}
