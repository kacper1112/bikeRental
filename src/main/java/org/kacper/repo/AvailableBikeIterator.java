package org.kacper.repo;

import org.kacper.Rental;
import org.kacper.rental_items.Bike;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableBikeIterator {
    private final List<Bike> availableBikes;
    private final Iterator<Bike> bikeIterator;
    
    public AvailableBikeIterator() {
        availableBikes = BikeDataMapper.getInstance()
                .getAllBikes()
                .stream()
                .filter(AvailableBikeIterator::isBikeAvailable)
                .collect(Collectors.toList());
        bikeIterator = availableBikes.iterator();
    }

    public boolean hasNext() {
        return bikeIterator.hasNext();
    }

    public Bike next() {
        return bikeIterator.next();
    }

    private static boolean isBikeAvailable(Bike bike) {
        List<Rental> rentals = RentalDataMapper.getInstance().getAllRentals();
        LocalDateTime now = LocalDateTime.now();
        
        for(Rental rental : rentals) {
            if(rental.getRentalItems().contains(bike) &&
                    now.isAfter(rental.getFrom()) &&
                    now.isBefore(rental.getTo())) {
                return false;
            }
        }
        
        return true;
    }
}
