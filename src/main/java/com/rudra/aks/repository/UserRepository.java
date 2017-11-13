package com.rudra.aks.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudra.aks.model.UserBO;

public interface UserRepository extends JpaRepository<UserBO, Serializable> {

	Optional<UserBO> findOneByUsername(String username);
}
