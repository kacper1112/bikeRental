package org.kacper.menu;

public class EmployeeMenu {
    public static void show() {

        System.out.println("Co chcesz zrobić?\n" +
                "0. Dodaj nowy rower.\n" +
                "1. Dodaj nowy rodzaj akcesorium\n" +
                "2. Dodaj nowe akcesorium.\n" +
                "3. Dodaj nowego klienta.\n" +
                "4. Dodaj nowego pracownika.\n" +
                "5. Dodaj nową rezerwację.\n" +
                "6. Generuj raport tygodniowy.");
        
        int input = MenuUtil.readUserInput(6);
        
        while(input != 6) {
            switch(input) {
                case 0:
                    RepoOperationMenu.addBike();
                    break;
                case 1:
                    RepoOperationMenu.addAccessoryType();
                    break;
                case 2:
                    RepoOperationMenu.addAccessory();
                    break;
                case 3:
                    RepoOperationMenu.addCustomer();
                    break;
                case 4:
                    RepoOperationMenu.addEmployee();
                    break;
                case 5:
                    RepoOperationMenu.addRental();
                    break;
                case 6:
                    return;
                //break;
            }
            input = MenuUtil.readUserInput(6);
        }
    }
}
