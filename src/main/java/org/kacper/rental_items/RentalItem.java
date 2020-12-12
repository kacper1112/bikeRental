package org.kacper.rental_items;


public abstract class RentalItem {
    private final int id;
    private final String name;
    private final Double pricePerHour;
    
    protected RentalItem(int id, String name, Double pricePerHour) {
        this.id = id;
        this.name = name;
        this.pricePerHour = pricePerHour;
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
