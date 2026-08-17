package com.ylh.ui;
import com.ylh.entities.User;
import com.ylh.service.BankService;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputHandler {

    private Scanner scanner;
    private BankService bankService;
    final Pattern MYANMAR_PH_REGEX = Pattern.compile("^(09\\d{7,9}|01\\d{5,7})$");

    public UserInputHandler(Scanner scanner, BankService bankService) {
        this.scanner = scanner;
        this.bankService = bankService;
    }

    public String readFullName() {
        String fullName;
        while (true) {
            System.out.print("Full Name : ");
             fullName = scanner.nextLine();
             if(isValidName(fullName)){
                 System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .                                                
                                .  👤 Name Created Successfully! %s ☑️                                               
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """, fullName);
                 break;
             } else {
                 System.out.println("Name Creation Failed! Try Again");
             }
        }
        return fullName;
    }

    public boolean isValidName(String fullName){
        boolean hasValidator = false;
        String trimmedName = fullName.trim();
        if (fullName == null || fullName.isEmpty() || fullName.isBlank()) {
            System.out.println("Name can't be empty!");
            return false;
        } else if (!trimmedName.contains(" ")) {
            System.out.println("Please Enter Your Full Name!");
            return false;
        }

        for (int i = 0; i < trimmedName.length(); i++) {
            char ch = trimmedName.charAt(i);
            if (!Character.isLetter(ch) && ch != ' ' && ch != '-' && ch != '\'') {
                hasValidator = false;
            } else {
                hasValidator = true;
            }
        }
        if (!hasValidator) {
            System.out.println("Name must Include Letter, space, hyphen and apostrophe only!");
        }
        return hasValidator;
    }

    public String readPsw(){
        String psw;
        while (true) {
            System.out.print("Create Password : ");
            psw = scanner.nextLine();
            if (isValidPassword(psw)){
                System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .                                                
                                .    🔐 Password Created Successfully! 🔐                                               
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                break;
            }
        }
        return psw;
    }

    public boolean isValidPassword(String psw){
        if (psw.length() < 8 || psw.length() > 8) {
            System.out.println("Password must have exact 8 characters!");
            return false;
        }

        if (psw == null || psw.isBlank() || psw.isEmpty()) {
            System.out.println("⚠️ Enter Your Password! ⚠️");
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasWhiteSpace = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>/?";

        for (char ch : psw.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            if (Character.isLowerCase(ch)) hasLower = true;
            if (Character.isDigit(ch)) hasDigit = true;
            if (specialChars.contains(String.valueOf(ch))) hasSpecialChar = true;
            if (Character.isWhitespace(ch)) hasWhiteSpace = false;
        }

        if (hasUpper && hasLower && hasDigit && !hasWhiteSpace && hasSpecialChar) {
            return true;
        } else {
            System.out.println("Password must contain Uppercase, Lowercase, Digit, Special Character, and 8 character!");
            return false;
        }
    }

    public LocalDate readDob(){
        LocalDate dateOfBirth = null;
        while (true) {
            System.out.print("Date Of Birth(YYYY-MM-DD) : ");
            String rawDOB;
            rawDOB = scanner.nextLine();
            if(isValidDob(rawDOB)){
                dateOfBirth = formatDate(rawDOB);
                System.out.printf("""
                                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                                    .    🎉✨  AGE VERIFIED SUCCESSFULLY!  ✨🎉                 
                                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                                    """);

                break;
            }

        }
        return dateOfBirth;
    }

    public <localDate> localDate formatDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(s, formatter);
        return (localDate) dateOfBirth;
    }

    public boolean isValidDob(String s){
        boolean isValidDob = false;
        try {
            LocalDate dateOfBirth = formatDate(s);
            LocalDate today = LocalDate.now();
            boolean isAdult = dateOfBirth.isBefore(today.minusYears(18));
            boolean isOldest = dateOfBirth.isAfter(today.minusYears(120));
            if (isAdult && isOldest) {
                isValidDob = true;
            } else {
                System.out.println("⚠️ Age must be after 18 and under 120! ⚠️");
                isValidDob = false;
            }
        } catch (DateTimeException e) {
            System.out.println("Illegal Year, Month or Date Provided!");
            isValidDob = false;
        }
        return isValidDob;
    }

    public String readEmail(){
        String email;
        while (true) {
            System.out.print("Email : ");
            email = scanner.nextLine();
            if(isValidEmail(email)){
                System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .    🎉✨  Email VERIFIED SUCCESSFULLY!  ✨🎉                   
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                break;
            }

        }
        return email;
    }

    public boolean isValidEmail(String email){
        boolean isValidEmail;
        if (email == null || !email.contains("@") || !email.contains("mail") || !email.contains("edu")) {
            System.out.println("Invalid Email");
             isValidEmail = false;
        } else if (email.contains("mail")){
            if(email.endsWith(".com")){
                isValidEmail = true;
            } else {
                System.out.println("Invalid Email");
                isValidEmail = false;
            }
        } else {
            isValidEmail = true;
        }
        return isValidEmail;
    }

    public String readPhNum(){
        String phNum;
        while (true) {
            System.out.print("Phone Number : ");
            phNum = scanner.nextLine();
            if(isValidPhNum(phNum)){
                System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .    🎉✨  PHONE NUMBER ADDED SUCCESSFULLY!  ✨🎉                
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                break;
            }

        }
        return phNum;
    }

    public boolean isValidPhNum(String phNum){
        boolean isValidPhNum;
        String cleanPhNum = phNum.replaceAll("[\\s\\-\\(\\)]", "");
        Matcher matcher = MYANMAR_PH_REGEX.matcher(cleanPhNum);

        if(cleanPhNum == null){
            System.out.println("⚠️ Enter Your Phone Number! ⚠️");
            isValidPhNum = false;
        } else if(matcher.matches()){
            isValidPhNum = true;
        } else {
            System.out.println("Invalid Phone Number! Try Again!");
            isValidPhNum = false;
        }
        return isValidPhNum;
    }

    public double readBalance(){
        String input;
        double balance = 0;
        while(true) {
            System.out.print("Initial Balance ($): ");
            input = scanner.nextLine();
            if(isValidBalance(input)){
                balance = Double.parseDouble(input);
                System.out.printf("""
                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                    .    🎉✨  Your Balance : $ %.2f  ✨🎉                   
                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                    """, balance);

                break;
            }
        }
        return balance;
    }

    public boolean isValidBalance(String input){
        if(input == null || input.trim().isEmpty()){
            System.out.println("Please Enter the Balance!");
            return false;
        }
        double balance;
        try{
            balance = Double.parseDouble(input);
            if(balance <= 0){
                System.out.println("Balance cannot be negative or 0!");
                return false;
            }
           return true;
        } catch (NumberFormatException e){
            System.out.println("⚠️ You entered a character, Just enter Numbers!");
            return false;
        }
    }

    public void readIsActive(){
        System.out.print("Is Active(Y/N) : ");
        String isActiveInput = scanner.nextLine();
        boolean isActive = false;
        if (isActiveInput.equalsIgnoreCase("Y")) {
            isActive = true;
        }
    }

    //UPDATE

    public User validateUser(){
        while(true){
            System.out.print("Enter User ID : ");
            int userId;
            try{
                userId = scanner.nextInt();
                scanner.nextLine();
            } catch(Exception e){
                System.out.println("ID must be a number!");
                scanner.nextLine();
                continue;
            }

            User user = bankService.findUserById(userId);

            if(user == null){
                System.out.println("User ID not found!");
                continue;
            }
            System.out.print("Enter Password : ");
            String userPsw = scanner.nextLine();
            if (userPsw.equals(user.getPsw())) {

                System.out.printf("""
                ✿ ✿ ✿ ✿ ✿  Welcome, %s!  ✿ ✿ ✿ ✿ ✿
                """, user.getFullName());

                return user;
            } else {
                System.out.println("⚠️ Incorrect Password! Try again.");
            }
        }
    }


    public String readNewName(){
        String newName;
        while (true) {
            System.out.print("Full Name : ");
            newName = scanner.nextLine();
            if(isValidName(newName)){
             return newName;
            } else {
                return null;
            }
        }
    }

    public void handledUpdatedUserName(User user){
        String newName = readNewName();
        if(bankService.updateUserName(user,newName)){
            System.out.printf("""
    ························································
    ·        ✅ Name Updated Successfully!               
    ·        👤 Your name is set to: %s                  
    ························································
    """, newName);
        } else {
            System.out.println("⚠️ Update failed! Invalid name provided.");
        }
    }

    public String readNewPsw(){
        System.out.print("Enter New Psw : ");
        String newPsw = scanner.nextLine();
        if(isValidPassword(newPsw)){
            return newPsw;
        } else {
            return null;
        }
    }

    public void handledUpdatedUserPsw(User user){
        String newPsw = readNewPsw();
        if(bankService.updateUserPsw(user, newPsw)){
            System.out.printf("""
    ························································
    ·        🔐 Password Updated Successfully! ☑️                               
    ························································
    """);
        }else {
            System.out.println("⚠️ Update failed! Invalid Password provided.");
        }
    }

    public LocalDate readNewDob(){
        System.out.print("Enter New DOB : ");
        String newDob = scanner.nextLine();
        if(isValidDob(newDob)){
            LocalDate dob = formatDate(newDob);
            return dob;
        } else {
            return null;
        }
    }

    public void handledUpdatedUserDob(User user){
        LocalDate newDob = readNewDob();
        if(bankService.updateUserDob(user, newDob)){
            System.out.printf("""
    ························································
    ·        🔐 Date Of Birth is Updated Successfully! ☑️                               
    ························································
    """);
        }else {
            System.out.println("⚠️ Update failed! Invalid DOB provided.");
        }
    }

    public String readNewEmail(){
        System.out.print("Enter New Email : ");
        String newEmail = scanner.nextLine();
        return newEmail;
    }

    public String readNewPhNum(){
        System.out.print("Enter New Phone Number : ");
        String newPhNum = scanner.nextLine();
        return newPhNum;
    }


//    public void updateUserEmail(String newEmail){
//        User user = validateUser();
//        user.setEmail(newEmail);
//        System.out.printf("""
//    ························································
//    ·        📧 Email Updated Successfully! ☑️
//    ························································
//    """);
//    }
//
//    public void updateUserPhNum(String newPhNum){
//        int index = validateUser();
//        userAccounts.get(index).setEmail(newPhNum);
//        System.out.printf("""
//    ························································
//    ·        📞 Phone Number Updated Successfully! ☑️
//    ························································
//    """);
//    }

    static boolean goToMenu = false;
    public static boolean goToMenu() throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to go to Main Menu? (yes/no) : ");
        String inputToMenu = scanner.nextLine();
        if(inputToMenu.equalsIgnoreCase("yes")){
            showLoading("Main Menu");
            goToMenu =  true;
        } else if (inputToMenu.equalsIgnoreCase("no")) {
            System.out.println("All your accounts will be lost as this is Temporary Array, Are You Sure?(Yes/no) : ");
            String doubleCheckInput = scanner.nextLine();
            if(doubleCheckInput.equalsIgnoreCase("yes")){
                goToMenu = false;
            } else if (doubleCheckInput.equalsIgnoreCase("no")){
                showLoading("Main Menu");
                goToMenu = true;
            }
        }
        return goToMenu;
    }

    public static void showLoading(String text) throws InterruptedException {
        for(int i = 3; i >= 0; i--){
            String[] spinner = {"◐", "◓", "◑", "◒"};
            System.out.printf("\r┃ ⏳ %s Going to %s in %ds ┃", spinner[i % 4],text, i);
            System.out.println();
            Thread.sleep(1000);
        }
    }

    public boolean wantToUpdateOtherFields(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to update other fields? (yes/no) : ");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("yes")){
            return true;
        } else if(input.equalsIgnoreCase("no")){
            return false;
        } else {
            System.out.println("Invalid Response");
            return false;
        }
    }


}