package com.rudra.aks.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudra.aks.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Serializable>{

}
