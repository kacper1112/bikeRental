package org.kacper;

import org.kacper.repo.Repo;

public class bikeRental {
    
    
    public static void main(String[] args) {
        Repo.getInstance().addEmployee("Jan", "Kowalewski", "11233445677", "abcde");
        Repo.getInstance().addEmployee("Maria", "Nowak", "00998877655", "aaaaa");
    }
}
