package org.kacper.menu;

import java.util.Scanner;

public class MenuUtil {
    protected static int readUserInput(int range) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        
        System.out.println("Twoj wybor: ");
        
        while(choice < 0 || range < choice) {
            try {
                choice = scanner.nextInt();
            } catch(Exception ex) {
                System.out.println("Spróbuj ponownie: ");
            }
        }
        
        return choice;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
