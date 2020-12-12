package org.kacper.rental_items;

public class Accessory extends RentalItem {
    private final String description;
    private final AccessoryType type;
    
    public Accessory(int id, Double pricePerHour, String name, String description, AccessoryType type) {
        super(id, name, pricePerHour);
        this.description = description;
        this.type = type;
    }
}
