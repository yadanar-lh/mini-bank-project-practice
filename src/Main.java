
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

    menu_loop:
    while(true){
        MenuHandler.showMainMenu();
        System.out.print("Enter : ");
        int userChosenNum = scanner.nextInt();
        scanner.nextLine();
        switch(userChosenNum) {
            case 1: MenuHandler.showCreateMenu();
            if(MenuHandler.goToMenu()){
                continue;
            } else {
                break menu_loop;
            }
            case 2: MenuHandler.showUpdateMenu();
                if(MenuHandler.goToMenu()){
                    continue;
                } else {
                    break menu_loop;
                }
        }

    }




}
