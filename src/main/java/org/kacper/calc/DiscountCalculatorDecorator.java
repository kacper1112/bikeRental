package org.kacper.calc;

import org.apache.commons.math3.util.Precision;
import org.kacper.Rental;

public class DiscountCalculatorDecorator implements PriceCalculator {
    private final String name = "Discounted ";
    private final PriceCalculator calculator;
    
    public DiscountCalculatorDecorator(PriceCalculator calculator) {
        this.calculator = calculator;
    }
    
    @Override
    public double calculatePrice(Rental rental) {
        double price = calculator.calculatePrice(rental);
        double discount = (double)rental.getCustomer().getPermanentDiscount() / 100;
        
        return Precision.round(price * discount, 2);
    }
    
    @Override
    public String getName() {
        return name + calculator.getName();
    }
}
