
import com.ylh.service.BankService;
import com.ylh.ui.MenuHandler;
import com.ylh.ui.UserInputHandler;

import javax.swing.*;

import java.util.Scanner;
void main() throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    BankService bankService = new BankService();
    UserInputHandler userInputHandler = new UserInputHandler(scanner,bankService);
    MenuHandler menuHandler = new MenuHandler(userInputHandler,bankService);

    while(true){
        MenuHandler.showMainMenu();

        System.out.print("Enter : ");
        int userChosenNum = scanner.nextInt();
        scanner.nextLine();

        switch(userChosenNum) {
            case 1: MenuHandler.showCreateMenu(); break;
            case 2 : MenuHandler.showAccountInfo(); break;
            case 3: MenuHandler.showUpdateMenu(); break;
            default:
                System.out.println("Invalid Input! Please Enter only 1-5!");
                continue;
        }
        if(!userInputHandler.goToMenu()){
            break;
        }

    }




}
