package org.kacper;

import org.kacper.util.PriceCalculator;
import org.kacper.rental_items.RentalItem;

import java.time.LocalTime;
import java.util.List;

public class Rental {
    private LocalTime from;
    private LocalTime to;
    private Customer customer;
    private List<RentalItem> rentalItems;
    private PriceCalculator priceCalculator;

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<RentalItem> getRentalItems() {
        return rentalItems;
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }
}
