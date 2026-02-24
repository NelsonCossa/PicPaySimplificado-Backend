package com.devnelson.picpay_simplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devnelson.picpay_simplificado.dtos.UserDTO;
import com.devnelson.picpay_simplificado.entities.user.User;
import com.devnelson.picpay_simplificado.entities.user.UserType;
import com.devnelson.picpay_simplificado.repositories.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(sender.getUserType() == UserType.MERCHANT) {
		throw new Exception ("Usuario do tipo logista nao esta autorizado a realizar transaccao");	
		}
		if(sender.getBalance().compareTo(amount)<0) {
			throw new Exception("Saldo insuficiente");
		}
		
	}
	
	public User findUserById(Long id) throws Exception {
		return this.repository.findById(id).orElseThrow(()-> new Exception ("Usuario nao encontrado"));
		
	}
	
	public void saveUser(User user) {
		this.repository.save(user);
	}
	
	public User createUser(UserDTO data) {
		User newUser=new User(data);
		 this.saveUser(newUser);
		 return newUser;
		
	}

	public List<User> getAllUsers() {
		return this.repository.findAll();
	}

}
