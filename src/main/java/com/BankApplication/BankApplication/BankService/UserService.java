package com.BankApplication.BankApplication.BankService;

import com.BankApplication.BankApplication.dto.*;

public interface UserService  {

BankResponse CreateUser(UserRequest userRequest);

BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);
String nameEnquiry(EnquiryRequest enquiryRequest);
BankResponse CreditAmount(CreditDebitRequest creditDebitRequest);

BankResponse DebitAmount(CreditDebitRequest creditDebitRequest);

BankResponse transferAmount(TransferRequest transferRequest);
}
