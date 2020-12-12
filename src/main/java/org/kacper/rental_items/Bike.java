package org.kacper.rental_items;

public final class Bike extends RentalItem {
    
    private final String make;
    private final String frameSize;
    private final String wheelSize;
    
    //optional
    private final String suspension;
    private final String frameNumber;

    public Bike(Builder builder) {
        super(builder.getId(), builder.getName(), builder.getPricePerHour());
        this.make = builder.make;
        this.suspension = builder.suspension;
        this.frameSize = builder.frameSize;
        this.wheelSize = builder.wheelSize;
        this.frameNumber = builder.frameNumber;
    }
    
    
    public static class Builder extends RentalItem {
        private final String make;
        private final String frameSize;
        private final String wheelSize;

        //optional
        private String suspension;
        private String frameNumber;
        
        public Builder(int id, Double pricePerHour, String name, String make,
                       String frameSize, String wheelSize) {
            super(id, name, pricePerHour);
            this.make = make;
            this.frameSize = frameSize;
            this.wheelSize = wheelSize;
        }
        
        public Builder withSuspension(String suspension) {
            this.suspension = suspension;
            return this;
        }
        
        public Builder withFrameNumber(String frameNumber) {
            this.frameNumber = frameNumber;
            return this;
        }
        
        public Bike build() {
            return new Bike(this);
        }
    }
}
