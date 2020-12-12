package org.kacper.menu;

import org.kacper.repo.RepoOperation;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RepoOperationMenu {
    public static void addEmployee() {
        Scanner in = new Scanner(System.in);
        String name, surname, pesel, password;
        
        System.out.println("Podaj imie pracownika:");
        name  = in.next();
        System.out.println("Podaj nazwisko pracownika:");
        surname = in.next();
        System.out.println("Podaj pesel pracownika:");
        pesel = in.next();
        System.out.println("Podaj hasło pracownika:");
        password = in.next();

        RepoOperation.getInstance().addEmployee(
                name,
                surname,
                pesel,
                password
        );

        System.out.println("Dodano pracownika. Wciśnij enter, aby kontynuować");
        
        MenuUtil.pressAnyKey();
        //in.close();
    }
    
    public static void addAccessoryType() {
        Scanner in = new Scanner(System.in);
        String type;
        
        System.out.println("Podaj nowy typ akcesoriów:");
        type = in.next();
        
        RepoOperation.getInstance().addAccessoryType(type);

        System.out.println("Dodano typ akcesorium. Wciśnij enter, aby kontynuować");

        MenuUtil.pressAnyKey();
        //in.close();
    }
    
    public static void addAccessory() {
        Scanner in = new Scanner(System.in);
        String name, desc, type;
        double price;

        System.out.println("Podaj nazwę akcesorium:");
        name = in.next();
        System.out.println("Podaj cenę wypożyczenia akcesorium na godzinę:");
        price = in.nextDouble();
        System.out.println("Podaj opis akcesorium:");
        desc = in.next();
        System.out.println("Podaj typ akcesorium:");
        type = in.next();
        
        RepoOperation.getInstance().addAccessory(name, price, desc, type);

        System.out.println("Dodano akcesorium. Wciśnij enter, aby kontynuować");
        
        MenuUtil.pressAnyKey();
        //in.close();
    }
    
    public static void addBike() {
        Scanner in = new Scanner(System.in);
        String name, make, suspension, frameSize, wheelSize, frameNumber;
        double price;

        System.out.println("Podaj nazwę roweru:");
        name = in.nextLine();
        System.out.println("Podaj cenę wypożyczenia roweru na godzinę:");
        price = in.nextDouble();
        System.out.println("Podaj markę roweru:");
        make = in.next();
        System.out.println("Podaj rozmiar ramy:");
        frameSize = in.next();
        System.out.println("Podaj rozmiar kół:");
        wheelSize = in.next();
        System.out.println("Opcjonalne: podaj informacje o zawieszeniu roweru (wpisz '-' aby pominąć):");
        suspension = in.next();
        suspension = suspension.equals("-") ? null : suspension;
        System.out.println("Opcjonalne: podaj numer ramy roweru (wpisz '-' aby pominąć):");
        frameNumber = in.next();
        frameNumber = frameNumber.equals("-") ? null : frameNumber;
        
        RepoOperation.getInstance().addBike(name, price, make, frameSize, wheelSize, suspension, frameNumber);

        System.out.println("Dodano rower. Wciśnij enter aby kontynuować");
        
        MenuUtil.pressAnyKey();
        //in.close();
    }
    
    public static void addCustomer() {
        Scanner in = new Scanner(System.in);
        String name, surname, pesel, password, phone, email, temp;
        Integer discount;

        System.out.println("Podaj imie klienta:");
        name  = in.next();
        System.out.println("Podaj nazwisko klienta:");
        surname = in.next();
        System.out.println("Podaj pesel klienta:");
        pesel = in.next();
        System.out.println("Podaj hasło klienta:");
        password = in.next();
        System.out.println("Opcjonalnie: podaj nr telefonu klienta (wpisz '-' by pominąć):");
        temp = in.next();
        phone = temp.equals("-") ? null : temp;
        System.out.println("Opcjonalnie: podaj email klienta (wpisz '-' aby pominąć):");
        temp = in.next();
        email = temp.equals("-") ? null : temp;
        System.out.println("Opcjonalnie: podaj stałą zniżkę klienta (wpisz 0 aby pominąć):");
        discount = in.nextInt();
        discount = discount == 0 ? null : discount;
        
        RepoOperation.getInstance().addCustomer(name, surname, pesel, password, phone, email, discount);

        System.out.println("Dodano klienta. Wciśnij enter aby kontynuować");
        
        MenuUtil.pressAnyKey();
        //in.close();
    }
    
    public static void addRental() {
        Scanner in = new Scanner(System.in);
        int customerId, hours, temp;
        List<Integer> rentalBikes = new LinkedList<>();
        List<Integer> rentalAccessories = new LinkedList<>();

        System.out.println("Podaj czas trwania wypożyczenia w pełnych godzinach (od teraz)");
        hours = in.nextInt();

        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(hours);

        System.out.println("Podaj id wypożyczającego klienta:");
        customerId = in.nextInt();

        System.out.println("Podaj id wypożyczanych rowerów, każdy w nowej linijce. Aby " +
                "zakończyć wprowadź -1");
        
        temp = in.nextInt();
        while(temp != -1) {
            rentalBikes.add(temp);
            temp = in.nextInt();
        }

        System.out.println("Podaj id wypożyczanych akcesoriów, każdy w nowej linijce. Aby " +
                "zakończyć wprowadź -1");

        temp = in.nextInt();
        while(temp != -1) {
            rentalAccessories.add(temp);
            temp = in.nextInt();
        }
        
        RepoOperation.getInstance().addRental(from, to, customerId, rentalBikes, rentalAccessories);

        System.out.println("Dodano wypożyczenie. Wciśnij enter aby kontynuować.");
        MenuUtil.pressAnyKey();
        
        //in.close();
    }
}























