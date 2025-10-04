package com.BankApplication.BankApplication.repository;

import com.BankApplication.BankApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    Boolean existsByEmail(String email);
    boolean existsByAccountNumber(String accountnumber);
    public User findByAccountNumber(String accountNumber);

}
