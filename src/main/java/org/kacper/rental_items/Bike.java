package org.kacper.rental_items;

import java.util.Objects;

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

    public String getMake() {
        return make;
    }

    public String getFrameSize() {
        return frameSize;
    }

    public String getWheelSize() {
        return wheelSize;
    }

    public String getSuspension() {
        return suspension;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        String suspension = this.getSuspension();
        String frameNumber = this.getFrameNumber();

        return
            this.getName() + "\n" +
            this.getMake() + "\n" +
            this.getPricePerHour() + " pln/h\n" +
            "rozmiar ramy: " + this.getFrameSize() + "\n" +
            "rozmiar kół: " + this.getWheelSize() + "\n" +
            ((suspension == null) ? "" : suspension + "\n") +
            ((frameNumber == null) ? "" : frameNumber + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(getMake(), bike.getMake()) &&
                Objects.equals(getFrameSize(), bike.getFrameSize()) &&
                Objects.equals(getWheelSize(), bike.getWheelSize()) &&
                Objects.equals(getSuspension(), bike.getSuspension()) &&
                Objects.equals(getFrameNumber(), bike.getFrameNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMake(), getFrameSize(), getWheelSize(), getSuspension(), getFrameNumber());
    }

    public static class Builder extends RentalItem {
        private final String make;
        private final String frameSize;
        private final String wheelSize;

        //optional
        private String suspension;
        private String frameNumber;
        
        
        
        public Builder(int id, String name, Double pricePerHour, String make,
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
