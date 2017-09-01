package com.rudra.aks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudra.aks.model.Privilege;
import com.rudra.aks.repository.PrivilegeRepository;
import com.rudra.aks.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	

	@Autowired
	PrivilegeRepository		privilegeRepository;

	@Override
	public boolean addPrivilege(Privilege privilege) {
		Privilege newPri = privilegeRepository.save(privilege);
		
		return newPri != null;
	}
	
	
}
