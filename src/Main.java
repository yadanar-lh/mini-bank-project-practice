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

        switch(userChosenNum){
            case 1 :
                System.out.printf("""
                    Create Your Account
                    -------------------
                    """);
                System.out.print("Full Name : ");
                String fullName = scanner.nextLine();

                System.out.print("Date Of Birth(YYYY-MM-DD) : ");
                String rawDOB = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfBirth = LocalDate.parse(rawDOB,formatter);

                System.out.print("Email : ");
                String email = scanner.nextLine();

                System.out.print("Phone Number : ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Initial Balance : ");
                double balance = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Is Active(Y/N) : ");
                String isActiveInput = scanner.nextLine();
                boolean isActive = false;
                if(isActiveInput.equalsIgnoreCase("Y")){
                    isActive = true;
                }

                bankService.createUser(fullName,dateOfBirth,email,phoneNumber,balance,isActive);
                break;
        }
    }




}
