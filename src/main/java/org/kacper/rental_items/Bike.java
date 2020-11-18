package org.kacper.rental_items;

public final class Bike extends RentalItem {
    
    private final String make;
    
    //optional
    private final String suspension;
    private final String frameSize;
    private final String wheelSize;
    private final String frameNumber;

    public Bike(Builder builder) {
        super(builder.getId(), builder.getPricePerHour(), builder.getName());
        this.make = builder.make;
        this.suspension = builder.suspension;
        this.frameSize = builder.frameSize;
        this.wheelSize = builder.wheelSize;
        this.frameNumber = builder.frameNumber;
    }
    
    
    public static class Builder extends RentalItem {
        private final String make;

        //optional
        private String suspension;
        private String frameSize;
        private String wheelSize;
        private String frameNumber;
        
        public Builder(Long id, Double pricePerHour, String name, String make) {
            super(id, pricePerHour, name);
            this.make = make;
        }
        
        public Builder withSuspension(String suspension) {
            this.suspension = suspension;
            return this;
        }
        
        
        public Builder withFrameSize(String frameSize) {
            this.frameSize = frameSize;
            return this;
        }
        
        public Builder withWheelSize(String wheelSize) {
            this.wheelSize = wheelSize;
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
