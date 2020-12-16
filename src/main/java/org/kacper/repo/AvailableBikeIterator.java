package org.kacper.repo;

import org.kacper.Rental;
import org.kacper.rental_items.Bike;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class AvailableBikeIterator {
    private final List<Bike> allBikes;
    private final Iterator<Bike> bikeIterator;
    
    public AvailableBikeIterator() {
        allBikes = RepoGetOperation.getInstance().getAllBikes();
        bikeIterator = allBikes.iterator();
    }
    
    public Bike nextAvailable() {
        Bike bike;
        
        if(!bikeIterator.hasNext()) {
            return null;
        }
        
        
        while(bikeIterator.hasNext()) {
            bike = bikeIterator.next();
            if(isBikeAvailable(bike)) {
                return bike;
            }
        }
        
        return null;
    }
    
    private static boolean isBikeAvailable(Bike bike) {
        List<Rental> rentals = RepoGetOperation.getInstance().getAllRentals();
        LocalDateTime now = LocalDateTime.now();
        
        for(Rental rental : rentals) {
            if(rental.getRentalItems().contains(bike) && rental.getTo().isBefore(now)) {
                return false;
            }
        }
        
        return true;
    }
}
