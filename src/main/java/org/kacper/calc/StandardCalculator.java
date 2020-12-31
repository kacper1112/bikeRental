package org.kacper.calc;

import org.apache.commons.math3.util.Precision;
import org.kacper.Rental;
import org.kacper.rental_items.RentalItem;

import static java.time.temporal.ChronoUnit.MINUTES;

// Weekends are paid 50% more

public class StandardCalculator implements PriceCalculator {
    private final String name = "Standard price calculator";
    
    @Override
    public double calculatePrice(Rental rental) {
        long timeDuringWeek = MINUTES.between(
                rental.getFrom(),
                rental.getTo());
        
        long timeDuringWeekends = MINUTES.between(
                rental.getFrom(),
                rental.getTo()
        );

        double sumOfPrices = rental.getRentalItems().stream()
                .map(RentalItem::getPricePerHour)
                .reduce(0D, Double::sum);
        
        
        double totalPrice = (timeDuringWeek / 60D) * sumOfPrices +
                (timeDuringWeekends / 60D) * sumOfPrices * 1.5D;


        totalPrice = Precision.round(totalPrice, 2);
        return totalPrice;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
