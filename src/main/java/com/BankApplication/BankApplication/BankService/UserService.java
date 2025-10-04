package com.BankApplication.BankApplication.BankService;

import com.BankApplication.BankApplication.dto.BankResponse;
import com.BankApplication.BankApplication.dto.CreditDebitRequest;
import com.BankApplication.BankApplication.dto.EnquiryRequest;
import com.BankApplication.BankApplication.dto.UserRequest;

public interface UserService  {

BankResponse CreateUser(UserRequest userRequest);

BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
String nameEnquiry(EnquiryRequest enquiryRequest);
BankResponse CreditAmount(CreditDebitRequest creditDebitRequest);

BankResponse DebitAmount(CreditDebitRequest creditDebitRequest);
}
