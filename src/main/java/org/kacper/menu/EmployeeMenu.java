package org.kacper.menu;

public class EmployeeMenu {
    public static void show() {

        System.out.println("Co chcesz zrobić?\n" +
                "0. Dodaj nowy sprzęt do bazy.\n" +
                "1. Dodaj nowego klienta do bazy.\n" +
                "2. Wyszukaj klienta po słowach kluczowych." +
                "3. Wyszukaj sprzęt po słowach kluczowych i właściwościach" +
                "4. Dokonaj rezerwacji w imieniu klienta." +
                "5. Generuj raport tygodniowy.");
        
        switch(MenuUtil.readUserInput(7)) {
            
        }
    }
}
