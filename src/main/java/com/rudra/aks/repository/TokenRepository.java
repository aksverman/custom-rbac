package com.rudra.aks.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudra.aks.model.PasswordResetToken;

public interface TokenRepository extends JpaRepository<PasswordResetToken, Serializable>{

	Optional<PasswordResetToken> findByToken(String token);

}
