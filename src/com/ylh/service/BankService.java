package com.ylh.service;

import com.ylh.dto.UserDto;
import com.ylh.entities.User;

import java.time.LocalDate;
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

    public boolean updateUserDob(User user, LocalDate newDob){
        if(user == null || newDob == null){
            return false;
        }
        user.setDateOfBirth(newDob);
        return true;
    }

    public boolean updateUserEmail(User user, String newEmail){
        if(user == null || newEmail == null){
            return false;
        }
        user.setPhoneNumber(newEmail);
        return true;
    }

    public boolean updateUserPhNum(User user, String newPhNum){
        if(user == null || newPhNum == null){
            return false;
        }
        user.setPhoneNumber(newPhNum);
        return true;
    }


}
