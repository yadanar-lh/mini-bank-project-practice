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
    ║        2.  ✧  Update Account                             ║
    ║        3.  ✧  Show Account Info                          ║
    ║        4.  ✧  Delete Account                             ║
    ║        5.  ✧  Show All Accounts                          ║
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

    static boolean goToMenu = false;
    public static boolean goToMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to go to Main Menu? (yes/no) : ");
        String inputToMenu = scanner.nextLine();
        if(inputToMenu.equalsIgnoreCase("yes")){
            goToMenu =  true;
        } else if (inputToMenu.equalsIgnoreCase("no")) {
            System.out.println("All your accounts will be lost as this is Temporary Array, Are You Sure?(Yes/no) : ");
            String doubleCheckInput = scanner.nextLine();
            if(doubleCheckInput.equalsIgnoreCase("yes")){
                goToMenu = false;
            } else if (doubleCheckInput.equalsIgnoreCase("no")){
                for(int i = 3; i >= 0; i--){
                    String[] spinner = {"◐", "◓", "◑", "◒"};
                    System.out.printf("\r┃ ⏳ %s Going to Main Menu in %ds ┃", spinner[i % 4], i);
                    System.out.println();
                    Thread.sleep(1000);
                }
                goToMenu = true;
            }
        }
        return goToMenu;
    }
}
