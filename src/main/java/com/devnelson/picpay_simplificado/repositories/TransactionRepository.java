package com.devnelson.picpay_simplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devnelson.picpay_simplificado.entities.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
