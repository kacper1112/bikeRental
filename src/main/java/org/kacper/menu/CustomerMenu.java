package org.kacper.menu;

public class CustomerMenu {
    public static void show() {
        System.out.println("Co chcesz zrobić?\n" +
                "0. Przeglądaj wszystkie dostępne rowery i akcesoria.\n" +
                "1. Wyszukaj rowery po słowach kluczowych i właściwościach.\n" +
                "2. Złóż prośbę o rezerwację.\n");
        
        switch (MenuUtil.readUserInput(2)) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
