package org.kacper.rental_items;

public class Accessory extends RentalItem {
    private final String description;
    private final AccessoryType type;
    
    public Accessory(int id, String name, Double pricePerHour, String description, AccessoryType type) {
        super(id, name, pricePerHour);
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public AccessoryType getType() {
        return type;
    }
    
    public String toString() {
        return
            this.getName() + "\n" +
            this.getPricePerHour() + "$/h\n" +
            this.getDescription() + "\n" +
            this.getType() + "\n\n";
    }
}
