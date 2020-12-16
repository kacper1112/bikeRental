package org.kacper;

import org.kacper.calc.PriceCalculator;
import org.kacper.rental_items.RentalItem;

import java.time.LocalDateTime;
import java.util.List;

public class Rental {
    private final int id;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final Customer customer;
    private final List<RentalItem> rentalItems;
    private PriceCalculator priceCalculator;

    public Rental(int id,
                  LocalDateTime from,
                  LocalDateTime to,
                  Customer customer,
                  List<RentalItem> rentalItems) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.customer = customer;
        this.rentalItems = rentalItems;
    }
    
    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<RentalItem> getRentalItems() {
        return rentalItems;
    }
    
    public void setPriceCalculator(PriceCalculator calculator) {
        this.priceCalculator = calculator;
    }

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }
}
