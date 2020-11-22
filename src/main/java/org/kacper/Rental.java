package org.kacper;

import org.kacper.util.PriceCalculator;
import org.kacper.rental_items.RentalItem;

import java.time.LocalTime;
import java.util.List;

public class Rental {
    private final int id;
    private final LocalTime from;
    private final LocalTime to;
    private final Customer customer;
    private final List<RentalItem> rentalItems;
    private final PriceCalculator priceCalculator;

    public Rental(int id,
                  LocalTime from,
                  LocalTime to,
                  Customer customer,
                  List<RentalItem> rentalItems,
                  PriceCalculator priceCalculator) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.customer = customer;
        this.rentalItems = rentalItems;
        this.priceCalculator = priceCalculator;
    }
    
    public int getId() {
        return id;
    }

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
