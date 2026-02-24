package com.devnelson.picpay_simplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devnelson.picpay_simplificado.entities.user.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	
Optional<User>findUserByDocument(String document);
Optional<User>findUserById(Long id);
}
