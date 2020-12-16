package org.kacper.menu;

public class InitMenu  {
    public static void show() {
        System.out.println("Witaj w systemie BikeRental\n" +
                "Co chcesz zrobić?\n" +
                "0. Zaloguj się do systemu.\n" +
                "1. Wyjdź\n");

        if (MenuUtil.readUserInput(2) == 0) {
            LoginMenu.show();
        } else {
            System.exit(0);
        }
    }
}
