import com.ylh.entities.User;
import com.ylh.service.BankService;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.Scanner;
void main() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    BankService bankService = new BankService();

    while(true){
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
        System.out.print("Enter : ");
        int userChosenNum = scanner.nextInt();
        scanner.nextLine();

        switch(userChosenNum) {
            case 1:
                System.out.printf("""
                        ════════════════════════════════════════════
                            ✧✧  CREATE YOUR ACCOUNT  ✧✧
                        ════════════════════════════════════════════
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
                        System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .                                                   
                                .    ✅ Name Created Successfully: %s ✅           
                                .                                                   
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """, fullName);
                        break;
                    }

                }

                String psw;
                while (true) {
                    System.out.print("Create Password : ");
                    psw = scanner.nextLine();

                    if (psw.length() < 0 || psw.length() > 8) {
                        System.out.println("Password must have up to 8 characters!");
                        continue;
                    }

                    if (psw == null || psw.isBlank() || psw.isEmpty()) {
                        System.out.println("⚠️ Enter Your Password! ⚠️");
                        continue;
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
                        System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .                                                  
                                .    🔐 Password Created Successfully! 🔐          
                                .                                                  
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                        break;
                    } else {
                        System.out.println("Password must contain Uppercase, Lowercase, Digit, Special Character, and 8 character!");
                    }
                }

                LocalDate dateOfBirth;
                while (true) {
                    System.out.print("Date Of Birth(YYYY-MM-DD) : ");
                    String rawDOB;
                    rawDOB = scanner.nextLine();

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        dateOfBirth = LocalDate.parse(rawDOB, formatter);
                        LocalDate today = LocalDate.now();
                        boolean isAdult = dateOfBirth.isBefore(today.minusYears(18));
                        boolean isOldest = dateOfBirth.isAfter(today.minusYears(120));
                        if (isAdult && isOldest) {
                            System.out.printf("""
                                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                                    .
                                    .    🎉✨  AGE VERIFIED SUCCESSFULLY!  ✨🎉        
                                    .             
                                    . . . . . . . . . . . . . . . . . . . . . . . . . .
                                    """);
                            break;
                        } else {
                            System.out.println("⚠️ Age must be after 18 and under 120! ⚠️");

                        }
                    } catch (DateTimeException e) {
                        System.out.println("Illegal Year, Month or Date Provided!");

                    }

                }


                String email;
                while (true) {
                    System.out.print("Email : ");
                    email = scanner.nextLine();
                    if (email == null || !email.contains("@")) {
                        System.out.println("Invalid Email");
                    } else {
                        System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .
                                .    🎉✨  Email VERIFIED SUCCESSFULLY!  ✨🎉        
                                .             
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                        break;
                    }
                }

                final Pattern MYANMAR_PH_REGEX = Pattern.compile("^(09\\d{7,9}|01\\d{5,7})$");
                String phoneNumber;
                while (true) {
                    System.out.print("Phone Number : ");
                    phoneNumber = scanner.nextLine();
                    String cleanPhNum = phoneNumber.replaceAll("[\\s\\-\\(\\)]", "");
                    if(cleanPhNum == null){
                        System.out.println("⚠️ Enter Your Phone Number! ⚠️");
                        continue;
                    }

                    Matcher matcher = MYANMAR_PH_REGEX.matcher(cleanPhNum);
                    if(matcher.matches()){
                        System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .
                                .    🎉✨  PHONE NUMBER ADDED SUCCESSFULLY!  ✨🎉        
                                .             
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """);
                        break;
                    } else {
                        System.out.println("Invalid Phone Number! Try Again!");
                    }
                }

                double balance;
                while(true){
                    boolean isValid = false;
                   try{
                       System.out.print("Initial Balance ($): ");
                       balance = scanner.nextDouble();
                       scanner.nextLine();
                       isValid = true;
                       System.out.printf("""
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                .
                                .    🎉✨  Your Balance : $ %.2f  ✨🎉        
                                .             
                                . . . . . . . . . . . . . . . . . . . . . . . . . .
                                """, balance);
                       break;

                   } catch (InputMismatchException e){
                       System.out.println("⚠️ You entered a character, Just enter Numbers!");
                   }
                }

                System.out.print("Is Active(Y/N) : ");
                String isActiveInput = scanner.nextLine();
                boolean isActive = false;
                if (isActiveInput.equalsIgnoreCase("Y")) {
                    isActive = true;
                }

                bankService.createUser(fullName, psw, dateOfBirth, email, phoneNumber, balance, isActive);

//                System.out.println("Do you want to Update Your Account? (yes/no)");
                System.out.println("Want to go to Main Menu? (yes/no)");
                String inputToMenu = scanner.nextLine();
                if(inputToMenu.equalsIgnoreCase("yes")){
                    continue;
                } else if (inputToMenu.equalsIgnoreCase("no")) {
                    System.out.println("All your accounts will be lost as this is Temporary Array, Are You Sure?");
                    String doubleCheckInput = scanner.nextLine();
                    if(doubleCheckInput.equalsIgnoreCase("yes")){
                        break;
                    } else if (doubleCheckInput.equalsIgnoreCase("no")){
                        for(int i = 3; i >= 0; i--){
                            String[] spinner = {"◐", "◓", "◑", "◒"};
                            System.out.printf("\r┃ ⏳ %s Going to Main Menu in %ds ┃", spinner[i % 4], i);
                            Thread.sleep(1000);

                        }
                        continue;
                    }
                }
                break;
        }

    }




}
