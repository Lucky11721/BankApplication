package com.BankApplication.BankApplication.BankServiceimpl;

import com.BankApplication.BankApplication.BankService.TransactionService;
import com.BankApplication.BankApplication.Entity.Transaction;
import com.BankApplication.BankApplication.dto.TransactionDto;
import com.BankApplication.BankApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("Success")
                .build();
        transactionRepository.save((transaction));
        System.out.println("Transaction completed succesfully");
    }
}
