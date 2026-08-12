package com.ylh.service;

import com.ylh.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BankService {

    Scanner scanner = new Scanner(System.in);
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

    public int validateUser(){
        while(true){
            System.out.print("Enter User ID : ");
            int userID = scanner.nextInt();
            scanner.nextLine();
            boolean isFound = false;
            int index = 0;

            for(User users : userAccounts){
                if(users.getAccountId() == userID){
                    index = userAccounts.indexOf(users);
                    isFound = true;
                    break;
                }
            }

            if(isFound){
                System.out.print("Enter Password : ");
                String userPsw = scanner.nextLine();
                boolean isMatched = false;
                if(userPsw.equals(userAccounts.get(index).getPsw())){
                    System.out.printf("""
    ✿ ✿ ✿ ✿ ✿  Welcome, %s!  ✿ ✿ ✿ ✿ ✿
    """, userAccounts.get(index).getFullName());
                    return index;
                }
            }

            if(!isFound){
                System.out.println("User ID not Found!");

            }
        }

    }

    public void updateUserName(String newName){
        int index = validateUser();
        userAccounts.get(index).setFullName(newName);
        System.out.printf("""
    ························································
    ·        ✅ Name Updated Successfully!               
    ·        👤 Your name is set to: %s                  
    ························································
    """, newName);
    }

    public void updateUserPsw(String newPsw){
        int index = validateUser();
        userAccounts.get(index).setPsw(newPsw);
        System.out.printf("""
    ························································
    ·        🔐 Password Updated Successfully! ☑️                               
    ························································
    """);
    }

    public void updateUserEmail(String newEmail){
        int index = validateUser();
        userAccounts.get(index).setEmail(newEmail);
        System.out.printf("""
    ························································
    ·        📧 Email Updated Successfully! ☑️                               
    ························································
    """);
    }

    public void updateUserPhNum(String newPhNum){
        int index = validateUser();
        userAccounts.get(index).setEmail(newPhNum);
        System.out.printf("""
    ························································
    ·        📞 Phone Number Updated Successfully! ☑️                               
    ························································
    """);
    }


}
