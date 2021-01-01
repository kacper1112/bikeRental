package org.kacper.calc;

import org.apache.commons.math3.util.Precision;
import org.kacper.Rental;
import org.kacper.rental_items.Bike;
import org.kacper.rental_items.RentalItem;

import static java.time.temporal.ChronoUnit.MINUTES;

// First time visitors pay only for bikes, weekend prices do not apply

public class FirstVisitCalculator implements PriceCalculator {
    private final String name = "First visit calculator";
    
    @Override
    public double calculatePrice(Rental rental) {
        
        long timeOfRental = MINUTES.between(
                rental.getFrom(),
                rental.getTo());
        
        double sumOfPrices = rental.getRentalItems().stream()
                .filter(rentalItem -> rentalItem instanceof Bike)
                .map(RentalItem::getPricePerHour)
                .reduce(0D, Double::sum);
        
        double totalPrice = (timeOfRental / 60D) * sumOfPrices;
        totalPrice = Precision.round(totalPrice, 2);
        
        return totalPrice;
    }

    @Override
    public String getName() {
        return name;
    }
}
