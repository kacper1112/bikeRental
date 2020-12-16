package org.kacper.menu;

import org.kacper.repo.Authenticator;

import java.util.Scanner;

public class LoginMenu {
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        
        System.out.println("Podaj pesel użytkownika:");
        username = scanner.next();
        System.out.println("Podaj hasło:");
        password = scanner.next();
        
        MenuUtil.clearScreen();

        switch(Authenticator.checkUser(username, password)) {
            case CUSTOMER:
                CustomerMenu.show();
                break;
            case EMPLOYEE:
                EmployeeMenu.show();
                break;
            default:
                System.out.println("Spróbuj ponownie\n");
                show();
        }
    }
}
