package com.rudra.aks.service;

import java.util.Optional;

import com.rudra.aks.model.Role;

public interface RoleService {

	boolean 	addRole(Role role);
	Optional<Role> 		findRoleWithRoleName(String roleName);
	Role		findRoleById(int roleid);
}
