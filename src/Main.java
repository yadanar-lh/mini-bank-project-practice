import com.ylh.entities.User;
import com.ylh.service.BankService;

import java.util.Scanner;
void main() {
    Scanner scanner = new Scanner(System.in);
    BankService bankService = new BankService();
    while(true){
        System.out.printf("""
            Welcome To Mini Bank
           ======================
           Please choose one
           1. Create Account
           2. Update Account
           3. Show Account Info
           4. Delete Account
           5. Show All Accounts
            """);
        System.out.print("Enter : ");
        int userChosenNum = scanner.nextInt();
        scanner.nextLine();

        switch(userChosenNum) {
            case 1:
                System.out.printf("""
                        Create Your Account
                        -------------------
                        """);

                String fullName;
                while (true) {
                    System.out.print("Full Name : ");
                    fullName = scanner.nextLine();
                    String trimmedName = fullName.trim();
                    if (fullName == null || fullName.isEmpty() || fullName.isBlank()) {
                        System.out.println("Please Enter Your Name!");
                        continue;
                    }

                    if (!trimmedName.contains(" ")) {
                        System.out.println("Please Enter Your Full Name!");
                        continue;
                    }

                    boolean hasValidator = false;
                    for (int i = 0; i < trimmedName.length(); i++) {
                        char ch = trimmedName.charAt(i);
                        if (!Character.isLetter(ch) && ch != ' ' && ch != '-' && ch != '\'') {
                            hasValidator = true;
                        }
                    }
                    if (hasValidator) {
                        System.out.println("Name must Include Letter, space, hyphen and apostrophe only!");
                    } else {
                        System.out.println("Name Created Successfully " + fullName + ".");
                        break;
                    }

                }

                String psw;
                while (true) {
                    System.out.print("Create Password : ");
                    psw = scanner.nextLine();

                    if( psw.length() < 0 || psw.length() > 8){
                        System.out.println("Password must have up to 8 characters!");
                        continue;
                    }

                    if(psw == null || psw.isBlank() || psw.isEmpty()){
                        System.out.println("Enter Your Password!");
                        continue;
                    }

                    boolean hasUpper = false;
                    boolean hasLower = false;
                    boolean hasDigit = false;
                    boolean hasWhiteSpace = false;
                    boolean hasSpecialChar = false;
                    String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>/?";

                    for( char ch : psw.toCharArray()){
                        if(Character.isUpperCase(ch)) hasUpper = true;
                        if(Character.isLowerCase(ch)) hasLower = true;
                        if(Character.isDigit(ch)) hasDigit = true;
                        if(specialChars.contains(String.valueOf(ch))) hasSpecialChar = true;
                        if(Character.isWhitespace(ch)) hasWhiteSpace = false;
                    }

                    if (hasUpper && hasLower && hasDigit && !hasWhiteSpace && hasSpecialChar){
                        System.out.println("Password Created Successfully!");
                        break;
                    } else {
                        System.out.println("Password must contain Uppercase, Lowercase, Digit, Special Character, and 8 character!");
                    }
                }

                System.out.print("Date Of Birth(YYYY-MM-DD) : ");
                String rawDOB = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfBirth = LocalDate.parse(rawDOB, formatter);

                System.out.print("Email : ");
                String email = scanner.nextLine();
                if (email == null || !email.contains("@")) {
                    System.out.println("Invalid Email");
                }

                System.out.print("Phone Number : ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Initial Balance : ");
                double balance = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Is Active(Y/N) : ");
                String isActiveInput = scanner.nextLine();
                boolean isActive = false;
                if (isActiveInput.equalsIgnoreCase("Y")) {
                    isActive = true;
                }

                bankService.createUser(fullName, psw, dateOfBirth, email, phoneNumber, balance, isActive);
                break;
        }
    }




}
