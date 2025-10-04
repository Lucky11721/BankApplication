package com.BankApplication.BankApplication.utils;

import java.time.Year;

public class AccountUtils {

    /*
    *
    * 2025+ randomsevenDigits
    *
    * */
   public static final String Account_exists = "001";
    public static final String Account_exists_message = "User is already has an account number";

  public static  final String Account_creation_succes = "002";
  public static final String Account_creeation_message = "Acoount created succesfully";

  public static  final  String Account_not_exist_code = "003";
  public static final String Account_not_exitst_message = "Account not exist with given account number";

  public static final String Account_found_code = "004";
  public static  final String Account_found_message = "Account found successfully";

 public static final String Credit_success_code = "005";
public static  final String Credit_succes_mesage = "Amount successfully credited to your bank account";

    public static final String Dedit_success_code = "006";
    public static  final String Dedit_succes_mesage = "Amount successfully Debited from your bank account";

    public static final String Insufficient_funds_code = "007";
    public static  final String Insufficient_fund_message = "Transaction rejected due to insufficient funds";


    public static String generateAccountNumber(){
        Year currectYear = Year.now();
        int min = 1000000;
        int max = 9999999;

        // generate random numberbetween min and max

        int randomNumber = (int)Math.floor(Math.random() * (max - min + 1 ) + min);

        //convert the current and randomnumber into Strings

        String year  = String.valueOf(currectYear);
        String random = String.valueOf(randomNumber);

        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(year);
        accountNumber.append(random);

        return accountNumber.toString();
    }




}
