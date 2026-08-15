package com.ylh.ui;

import com.ylh.dto.UserDto;
import com.ylh.entities.User;
import com.ylh.service.BankService;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuHandler {

    private static UserInputHandler inputHandler;
    private static BankService service;

    public MenuHandler(UserInputHandler inputHandler, BankService service) {
        this.inputHandler = inputHandler;
        this.service = service;
    }


    public static void showMainMenu(){
        System.out.printf("""
    ╔══════════════════════════════════════════════════════════╗
    ║                                                          ║
    ║    ★  ★  ★  M I N I  B A N K  ★  ★  ★                  ║
    ║                                                          ║
    ║    ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━    ║
    ║                                                          ║
    ║    ◆  Main Menu                                          ║
    ║                                                          ║
    ║        1.  ✧  Create Account                             ║
    ║        2.  ✧  Show Account Info                          ║
    ║        3.  ✧  Update Account                             ║
    ║        4.  ✧  Delete Account                             ║
    ║        5.  ✧  Withdraw Money                             ║
    ║                                                          ║
    ║    ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━    ║
    ║                                                          ║
    ╚══════════════════════════════════════════════════════════╝
    """);
    }

    public static void showCreateMenu(){
        System.out.printf("""
                        ════════════════════════════════════════════
                            ✧✧  CREATE YOUR ACCOUNT  ✧✧
                        ════════════════════════════════════════════
                        """);
        String fullName = inputHandler.readFullName();
        String psw = inputHandler.readPsw();
        LocalDate dob = inputHandler.readDob();
        String email = inputHandler.readEmail();
        String phone = inputHandler.readPhNum();
        double initialBalance = inputHandler.readBalance();

        //pack in dto
        UserDto dto = new UserDto(
                fullName,
                psw,
                dob,
                email,
                phone,
                initialBalance
        );
        //pass the dto to bank service
        service.createUser(dto);
        System.out.printf("""
    ─────────────────────────────────────────────────────────────────────
        ✨ ACCOUNT SUCCESSFULLY CREATED! ✨
        🎊 Thank you for joining Mini Bank! 🎊
    ─────────────────────────────────────────────────────────────────────
    """);
    }

    public static void showAccountInfo(){
        User user = inputHandler.validateUser();
        System.out.printf("""
    ─────────────────────────────────────────────────────────────────────
        ✨ ACCOUNT INFO ✨
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
    """, user.getAccountId(),user.getFullName(),user.getDateOfBirth(),user.getEmail(),user.getPhoneNumber(),user.getBalance(),"active" );
    }


    public static void showUpdateMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("""
                        ════════════════════════════════════════════
                            ✧✧  UPDATE YOUR ACCOUNT  ✧✧
                        ════════════════════════════════════════════
                        1. NAME
                        2. PASSWORD
                        3. EMAIL
                        4. PHONE NUMBER
                        ════════════════════════════════════════════
                        """);
        User loggedInUser = inputHandler.validateUser();
        System.out.print("Enter : ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        switch (userInput){
            case 1 : inputHandler.handledUpdatedUserName(loggedInUser); break;
            case 2 : inputHandler.handledUpdatedUserPsw(loggedInUser); break;
        }
    }


}
