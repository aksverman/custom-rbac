package com.rudra.aks.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudra.aks.model.Role;
import com.rudra.aks.repository.RoleRepository;
import com.rudra.aks.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	

	@Autowired
	RoleRepository		roleRepository;
	
	@Override
	public boolean addRole(Role role) {
		Role newRole = roleRepository.save(role);
		return newRole != null;
	}

	@Override
	public Optional<Role> findRoleWithRoleName(String roleName) {
		return roleRepository.findRoleByName(roleName);
	}

	@Override
	public Role findRoleById(int roleid) {
		return roleRepository.findOne(roleid);
	}
}
