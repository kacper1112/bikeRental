package org.kacper.rental_items;

public class Accessory extends RentalItem {
    private final String description;
    private final String type;
    
    public Accessory(int id, String name, Double pricePerHour, String description, String type) {
        super(id, name, pricePerHour);
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    
    public String toString() {
        return
            "nazwa: " + this.getName() + "\n" +
            "cena: " + this.getPricePerHour() + "$/h\n" +
            "opis: " + this.getDescription() + "\n" +
            "typ: " + this.getType() + "\n";
    }
}
