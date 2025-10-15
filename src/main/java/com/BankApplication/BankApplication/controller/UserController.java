package com.BankApplication.BankApplication.controller;

import com.BankApplication.BankApplication.BankService.UserService;
import com.BankApplication.BankApplication.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Account Management APIs", description = "APIs for creating accounts, checking balances, and performing transactions")
public class UserController {

    @Autowired
    UserService userService;

    // ---------------- Create Account ----------------
    @Operation(
            summary = "Create a new user account",
            description = "Creates a new user account and assigns an account ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.CreateUser(userRequest);
    }

    // ---------------- Balance Enquiry ----------------
    @Operation(
            summary = "Check account balance",
            description = "Fetches the current balance of a given account number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Balance retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.balanceEnquiry(enquiryRequest);
    }

    // ---------------- Name Enquiry ----------------
    @Operation(
            summary = "Get account holder name",
            description = "Fetches the name of the account holder for a given account number"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account holder name retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }

    // ---------------- Credit Amount ----------------
    @Operation(
            summary = "Credit money to account",
            description = "Credits the specified amount to a given account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amount credited successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction request")
    })
    @PostMapping("/credit")
    public BankResponse creditAmount(@RequestBody CreditDebitRequest creditDebitRequest){
        return userService.CreditAmount(creditDebitRequest);
    }

    // ---------------- Debit Amount ----------------
    @Operation(
            summary = "Debit money from account",
            description = "Debits the specified amount from a given account if sufficient balance is available"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amount debited successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "400", description = "Insufficient balance or invalid request")
    })
    @PostMapping("/debit")
    public BankResponse DebitAmount(@RequestBody CreditDebitRequest creditDebitRequest){
        return userService.DebitAmount(creditDebitRequest);
    }

    // ---------------- Transfer Amount ----------------
    @Operation(
            summary = "Transfer money between accounts",
            description = "Transfers funds from one account to another if sufficient balance is available"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amount transferred successfully"),
            @ApiResponse(responseCode = "404", description = "One or both accounts not found"),
            @ApiResponse(responseCode = "400", description = "Insufficient balance or invalid request")
    })
    @PostMapping("/transfer")
    public BankResponse TransferAmount(@RequestBody TransferRequest transferRequest){
        return userService.transferAmount(transferRequest);
    }
}
