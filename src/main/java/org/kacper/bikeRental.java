package org.kacper;

import org.kacper.repo.RepoOperation;

public class bikeRental {
    
    
    public static void main(String[] args) {
        var db = RepoOperation.getInstance();
        
        db.addEmployee("Jan", "Kowalewski", "11233445677", "abcde");
        
        db.addAccessoryType("blotnik");
        db.addAccessory("Blotnik długi", 10D, "super efektywny blotnik", "blotnik");
        
        db.test();
    }
}
