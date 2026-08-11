package com.ylh.service;

import com.ylh.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class BankService {

    private ArrayList<User> userAccounts = new ArrayList<>();
    private int accountId = 1;

    //Create User Account
    public void createUser(String fullName, String psw,LocalDate dateOfBirth, String email, String phoneNumber, double balance, boolean isActive){

        User newUser = new User(accountId,fullName,psw,dateOfBirth,email,phoneNumber,balance,isActive);
        userAccounts.add(newUser);

        //Show uer account created
        System.out.printf("""
    ─────────────────────────────────────────────────────────────────────
        ✨ ACCOUNT SUCCESSFULLY CREATED! ✨
    ─────────────────────────────────────────────────────────────────────
    
    📌  Account ID     : %d
    📌  Full Name      : %s
    📌  DOB            : %s
    📌  Email          : %s
    📌  Phone          : %s
    📌  Balance        : $%.2f
    📌  Status         : %s
    
    ─────────────────────────────────────────────────────────────────────
        🎊 Thank you for joining Mini Bank! 🎊
    ─────────────────────────────────────────────────────────────────────
    """, accountId, fullName, dateOfBirth, email, phoneNumber, balance, isActive);

        accountId++;

    }


}
