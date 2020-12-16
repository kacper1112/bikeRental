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
                "6. Przeglądaj wszystkie rowery.\n" +
                "7. Przeglądaj wszystkie akcesoria.\n" +
                "8. Znajdź informacje o rowerze po id.\n" +
                "9. Znajdź informacje o akcesorium po id.\n" +
                "10. Zobacz wszystkie dostępne rowery.\n" +
                "11. Generuj raport tygodniowy.");
        
        int input = MenuUtil.readUserInput(12);
        
        
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
                RepoOperationMenu.getAllBikes();
                break;
            case 7:
                RepoOperationMenu.getAllAccessories();
                break;
            case 8:
                RepoOperationMenu.getBikeById();
                break;
            case 9:
                RepoOperationMenu.getAccessoryById();
                break;
            case 10:
                RepoOperationMenu.getAllAvailableBikes();
                break;
            case 11:
                break;
            case 12:
                return;
        }
        show();
    }
}
