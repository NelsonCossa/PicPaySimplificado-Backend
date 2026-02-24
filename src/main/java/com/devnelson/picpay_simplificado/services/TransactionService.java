package com.devnelson.picpay_simplificado.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devnelson.picpay_simplificado.dtos.TransactionDTO;
import com.devnelson.picpay_simplificado.entities.transaction.Transaction;
import com.devnelson.picpay_simplificado.entities.user.User;
import com.devnelson.picpay_simplificado.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;  
	
	public void createTransaction (TransactionDTO transaction) throws Exception {
    User sender =this.userService.findUserById(transaction.senderId());
    User receiver=this.userService.findUserById(transaction.senderId());
    userService.validateTransaction(sender, transaction.value());
    
    boolean isAuthorize=this.authorizeTransaction(sender, transaction.value());
    if(!isAuthorize) {
    throw new Exception ("Transaccao nao autorizado");
    }
    
    Transaction newtransaction= new Transaction();
    
    newtransaction.setAmount(transaction.value());
    newtransaction.setReceiver(receiver);
    newtransaction.setSender(sender);
    
    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receiver.setBalance(receiver.getBalance().add(transaction.value()));
    
    this.repository.save(new Transaction());
    this.userService.saveUser(receiver);
    this.userService.saveUser(sender);
}
	
	public boolean authorizeTransaction(User sender, BigDecimal value) {
	ResponseEntity<Map> authorizationResponse=restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize" , Map.class);
	if(authorizationResponse.getStatusCode()==HttpStatus.OK ) {
		String message=(String) authorizationResponse.getBody().get("message");
		return "Autorizado".equalsIgnoreCase(message);
	}else return false;
	}
	
}