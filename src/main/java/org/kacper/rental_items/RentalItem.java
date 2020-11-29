package org.kacper.rental_items;


public abstract class RentalItem {
    private final int id;
    private final Double pricePerHour;
    private final String name;
    
    protected RentalItem(int id, Double pricePerHour, String name) {
        this.id = id;
        this.pricePerHour = pricePerHour;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public String getName() {
        return name;
    }
}
