import lombok.Data;

@Data
public final class Bike {
    private final String id;
    private final String make;
    private final String model;
    
    //optional
    private final String suspension;
    private final String[] accessories;
    private final String frameSize;
    private final String wheelSize;
    private final String frameNumber;

    public Bike(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.suspension = builder.suspension;
        this.accessories = builder.accessories;
        this.frameSize = builder.frameSize;
        this.wheelSize = builder.wheelSize;
        this.frameNumber = builder.frameNumber;
    }
    
    
    public static class Builder {
        private final String id;
        private final String make;
        private final String model;

        //optional
        private String suspension;
        private String[] accessories;
        private String frameSize;
        private String wheelSize;
        private String frameNumber;
        
        public Builder(String id, String make, String model) {
            this.id = id;
            this.make = make;
            this.model = model;
        }
        
        public Builder withSuspension(String suspension) {
            this.suspension = suspension;
            return this;
        }
        
        public Builder withAccessories(String[] accessories) {
            this.accessories = accessories;
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
