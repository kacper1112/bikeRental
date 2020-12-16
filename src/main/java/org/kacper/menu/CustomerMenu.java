package org.kacper.menu;

public class CustomerMenu {
    public static void show() {
        System.out.println("Co chcesz zrobić?\n" +
                "0. Przeglądaj wszystkie rowery.\n" +
                "1. Przeglądaj wszystkie akcesoria.\n" +
                "2. Znajdź informacje o rowerze po id.\n" +
                "3. Znajdź informacje o akcesorium po id.\n" +
                "4. Zobacz wszystkie dostępne rowery.\n" +
                "5. Zakończ\n");
        
        int input = MenuUtil.readUserInput(5);
        
        
        switch (input) {
            case 0:
                CustomerFacade.getAllBikes();
                break;
            case 1:
                CustomerFacade.getAllAccessories();
                break;
            case 2:
                CustomerFacade.getBikeById();
                break;
            case 3:
                CustomerFacade.getAccessoryById();
                break;
            case 4:
                CustomerFacade.getAllAvailableBikes();
                break;
            case 5:
                return;
            default:
                show();
                break;
        }
    }
}
