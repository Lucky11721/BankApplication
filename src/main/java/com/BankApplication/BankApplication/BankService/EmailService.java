package com.BankApplication.BankApplication.BankService;

import com.BankApplication.BankApplication.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
