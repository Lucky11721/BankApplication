package com.BankApplication.BankApplication.BankService;

import com.BankApplication.BankApplication.Entity.Transaction;
import com.BankApplication.BankApplication.dto.TransactionDto;

public interface TransactionService {
    void saveTransaction(TransactionDto transactionDto);
}
