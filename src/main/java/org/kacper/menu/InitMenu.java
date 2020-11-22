package org.kacper.menu;

public class InitMenu  {
    public static void show() {
        System.out.println("Witaj w systemie BikeRental\n" +
                "Co chcesz zrobić?\n" +
                "0. Zaloguj się do systemu.\n" +
                "2. Wyjdź\n");
        
        switch (MenuUtil.readUserInput(2)) {
            case 0:
                //LoginMenu.show();
                break;
            case 1:
                //CreateNewAccountMenu.show();
                break;
            default:
                System.exit(0);
        }
    }
}
