package org.kacper.calc;

import org.kacper.Rental;
import org.kacper.repo.CustomerDataMapper;

public class PriceCalculatorConfig {
    public static PriceCalculator getCalculator(Rental rental) {
        
        PriceCalculator calculator;
        
        
        int visits = CustomerDataMapper.getInstance().getCustomerRentalCount(rental.getCustomer().getId());
        
        if(visits > 1) {
            calculator = new StandardCalculator();
        } else {
            calculator = new FirstVisitCalculator();
        }
        
        
        if(rental.getCustomer().getPermanentDiscount() > 0) {
            return new DiscountCalculatorDecorator(calculator);
        }
        
        return calculator;
    }
}
