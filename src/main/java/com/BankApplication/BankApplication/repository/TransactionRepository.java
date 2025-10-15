package com.BankApplication.BankApplication.repository;

import com.BankApplication.BankApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long > {

}
