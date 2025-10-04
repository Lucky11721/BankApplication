package com.BankApplication.BankApplication.controller;

import com.BankApplication.BankApplication.BankService.UserService;
import com.BankApplication.BankApplication.dto.BankResponse;
import com.BankApplication.BankApplication.dto.CreditDebitRequest;
import com.BankApplication.BankApplication.dto.EnquiryRequest;
import com.BankApplication.BankApplication.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.CreateUser(userRequest);
    }

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        // Create an EnquiryRequest object from the request parameter
        return userService.balanceEnquiry(enquiryRequest);
    }

    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        // Create an EnquiryRequest object from the request parameter

        return userService.nameEnquiry(enquiryRequest);
    }

    @PostMapping("/Credit")
    public BankResponse creditAmount(@RequestBody CreditDebitRequest creditDebitRequest){
        return userService.CreditAmount(creditDebitRequest);
    }

    @PostMapping("/Debit")
    public BankResponse DebitAmount(@RequestBody CreditDebitRequest creditDebitRequest){
        return userService.DebitAmount(creditDebitRequest);
    }
}
