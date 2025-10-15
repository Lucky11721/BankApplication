package com.BankApplication.BankApplication.BankServiceimpl;

import com.BankApplication.BankApplication.BankService.EmailService;
import com.BankApplication.BankApplication.BankService.TransactionService;
import com.BankApplication.BankApplication.BankService.UserService;
import com.BankApplication.BankApplication.Entity.User;
import com.BankApplication.BankApplication.dto.*;
import com.BankApplication.BankApplication.repository.UserRepository;
import com.BankApplication.BankApplication.utils.AccountUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;
   @Autowired
   EmailService emailService;

   @Autowired
   TransactionService transactionService;

    @Override
    public BankResponse CreateUser(UserRequest userRequest) {
        /*
         * creating an account - saving a new user into the DB
         * check if the user already has an account
         * */
        if(userRepository.existsByEmail((userRequest.getEmail()))){
            BankResponse response = BankResponse.builder()
                    .responsecode(AccountUtils.Account_exists)
                    .responseMessage(AccountUtils.Account_exists_message)
                    .build();

            return response;
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(newUser);
        //Send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .Subject("Account Creation")
                .messageBody("Congratulation! your Account has been Succesfully Created . \n Your" +
                        "Account Details : \n Account Name : " + savedUser.getFirstName()  +
                        "\n Account Number : " + savedUser.getAccountNumber())
                .build();

        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
                .responsecode(AccountUtils.Account_creation_succes)
                .responseMessage(AccountUtils.Account_creeation_message) // Corrected typo
                .accountInfo(AccountInfo.builder()
                        .accountNumber(savedUser.getAccountNumber())
                        .accountBalance(savedUser.getAccountBalance())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .build())
                .build();
    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        /* check if provided account number exist or not
         */
        boolean isAccountExist = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
       if(!isAccountExist){
           return BankResponse.builder()
                   .responsecode(AccountUtils.Account_not_exist_code)
                   .responseMessage(AccountUtils.Account_not_exitst_message)
                   .build();
       }
       User foundUser = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());

       return BankResponse.builder()
               .responsecode(AccountUtils.Account_found_code)
               .responseMessage(AccountUtils.Account_found_message)
               .accountInfo(AccountInfo.builder()
                       .accountBalance(foundUser.getAccountBalance())
                       .accountNumber(foundUser.getAccountNumber())
                       .accountName(foundUser.getFirstName())
                       .build())
               .build();


    }

    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExist = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExist){
            return AccountUtils.Account_not_exitst_message;
        }
        User foundUser = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return foundUser.getFirstName() +" "+ foundUser.getLastName() +" ";
    }

    @Override
    public BankResponse CreditAmount(CreditDebitRequest creditRequest) {
         // checking if account exist or not
        boolean isAccountexist = userRepository.existsByAccountNumber(creditRequest.getAccountNumber());
        if(isAccountexist == false){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Account_not_exist_code)
                    .responseMessage(AccountUtils.Account_not_exitst_message)
                    .build();
        }

        User foundUser = userRepository.findByAccountNumber(creditRequest.getAccountNumber());

        foundUser.setAccountBalance(foundUser.getAccountBalance().add(creditRequest.getAmount()));
        userRepository.save(foundUser);

        TransactionDto transactionDto = TransactionDto.builder()
                .accountNumber(foundUser.getAccountNumber())
                .transactionType("Credit")
                .amount(creditRequest.getAmount())
                .build();
        transactionService.saveTransaction(transactionDto);


        return BankResponse.builder()
                .responsecode(AccountUtils.Credit_success_code)
                .responseMessage(AccountUtils.Credit_succes_mesage)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(foundUser.getAccountNumber())
                        .accountName(foundUser.getFirstName())
                        .accountBalance(foundUser.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public BankResponse DebitAmount(CreditDebitRequest creditDebitRequest) {
        boolean isAccountexist = userRepository.existsByAccountNumber(creditDebitRequest.getAccountNumber());

        if(isAccountexist == false){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Account_not_exist_code)
                    .responseMessage(AccountUtils.Account_not_exitst_message)
                    .build();
        }

        User foundUser = userRepository.findByAccountNumber(creditDebitRequest.getAccountNumber());
        BigInteger availabeBalance = foundUser.getAccountBalance().toBigInteger();
        BigInteger debitedBalance = creditDebitRequest.getAmount().toBigInteger();

        if(debitedBalance.intValue() > availabeBalance.intValue()){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Insufficient_funds_code)
                    .responseMessage(AccountUtils.Insufficient_fund_message)
                    .build();
        }
        else{
            foundUser.setAccountBalance(foundUser.getAccountBalance().subtract(creditDebitRequest.getAmount()));
            userRepository.save(foundUser);
            TransactionDto transactionDto = TransactionDto.builder()
                    .accountNumber(foundUser.getAccountNumber())
                    .transactionType("Ddebit")
                    .amount(creditDebitRequest.getAmount())
                    .build();

            transactionService.saveTransaction(transactionDto);
            return BankResponse.builder()
                    .responsecode(AccountUtils.Dedit_success_code)
                    .responseMessage(AccountUtils.Dedit_succes_mesage)
                    .accountInfo(AccountInfo.builder()
                            .accountName(foundUser.getFirstName())
                            .accountNumber(foundUser.getAccountNumber())
                            .accountBalance(foundUser.getAccountBalance())
                            .build())
                    .build();
        }


    }

    @Override
    @Transactional
    public BankResponse transferAmount(TransferRequest transferRequest) {
        // get the account to debit(check if account exist)
        // check if the amount if sufficient to dibit
        // debit the account
        // get the account to credit
        // credit the account
        System.out.println(transferRequest.getSenderAccountNumber());
        System.out.println(transferRequest.getReceiverAccountNumber());
        boolean isSenderAccountExist = userRepository.existsByAccountNumber(transferRequest.getSenderAccountNumber());
        boolean isReceiverAccountExist = userRepository.existsByAccountNumber(transferRequest.getReceiverAccountNumber());
        if(!isReceiverAccountExist){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Account_not_exist_code)
                    .responseMessage("Receiver " + AccountUtils.Account_not_exitst_message)
                    .accountInfo(null)
                    .build();
        }
        if(!isSenderAccountExist){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Account_not_exist_code)
                    .responseMessage("senderAccount  " + AccountUtils.Account_not_exitst_message)
                    .accountInfo(null)
                    .build();
        }

        User senderAccount = userRepository.findByAccountNumber(transferRequest.getSenderAccountNumber());
        if(transferRequest.getAmount().compareTo(senderAccount.getAccountBalance()) > 0){
            return BankResponse.builder()
                    .responsecode(AccountUtils.Insufficient_funds_code)
                    .responseMessage(AccountUtils.Insufficient_fund_message)
                    .accountInfo(null)
                    .build();
        }

        senderAccount.setAccountBalance(senderAccount.getAccountBalance().subtract(transferRequest.getAmount()));
        userRepository.save(senderAccount);
        String senderName = senderAccount.getFirstName() + " " + senderAccount.getLastName();
        EmailDetails debitAlert = EmailDetails.builder()
                .Subject("Debit Alert")
                .recipient(senderAccount.getEmail())
                .messageBody("The Sum of " + transferRequest.getAmount() + " has been debited from you account and your current balance is " + senderAccount.getAccountBalance() )
                .build();

        emailService.sendEmailAlert(debitAlert);

        User receiverAccount = userRepository.findByAccountNumber(transferRequest.getReceiverAccountNumber());
        receiverAccount.setAccountBalance(receiverAccount.getAccountBalance().add(transferRequest.getAmount()));

        userRepository.save(receiverAccount);

        EmailDetails creditAlert  = EmailDetails.builder()
                .Subject("Credit Alert")
                .recipient(receiverAccount.getEmail())
                .messageBody("Your account has been credited the amount " + transferRequest.getAmount() + "and your total account balance is" + receiverAccount.getAccountBalance())

                .build();

        emailService.sendEmailAlert(creditAlert);

        return BankResponse.builder()
                .responsecode(AccountUtils.Transfer_Succesfull_code)
                .responseMessage(AccountUtils.Trans_Succesfull_message)
                .accountInfo(null)
                .build();
    }


}
