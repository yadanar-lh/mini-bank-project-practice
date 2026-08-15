package com.ylh.service;

import com.ylh.dto.UserDto;
import com.ylh.entities.User;
import com.ylh.ui.UserInputHandler;

import java.util.ArrayList;

public class BankService {
    private ArrayList<User> userAccounts = new ArrayList<>();
    private int accountId = 1;

    //Create User Account Using DTO
    public User createUser(UserDto dto){

        User newUser = new User(
                accountId,
                dto.getFullName(),
                dto.getPassword(),
                dto.getDateOfBirth(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getBalance(),
                true
                );

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
    """, accountId,dto.getFullName(),dto.getDateOfBirth(),dto.getEmail(),dto.getPhoneNumber(),dto.getBalance(),"active" );

        userAccounts.add(newUser);
        accountId++;
        return newUser;

    }

    public User findUserById(int accountId){
        for(User user: userAccounts){
            if(user.getAccountId() == accountId){
                return user;
            }
        }
        return null; // ID not found
    }

    public boolean updateUserName(User user, String newName){
        if(user == null || newName == null){
            return false;
        }
        user.setFullName(newName);
        return true;
    }

    public boolean updateUserPsw(User user, String newPsw){
        if(user == null || newPsw == null){
            return false;
        }
        user.setPsw(newPsw);
        return true;
    }

//    public boolean updateUserEmail()


}
