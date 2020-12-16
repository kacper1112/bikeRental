package org.kacper.repo;

import org.kacper.Rental;
import org.kacper.util.FirstVisitCalculator;
import org.kacper.util.PriceCalculator;
import org.kacper.util.StandardCalculator;

public class PriceCalculatorConfig {
    public static PriceCalculator getCalculator(Rental rental) {
        int visits = RepoGetOperation.getInstance().getCustomerRentalCount(rental.getCustomer().getId());
        
        if(visits > 1) {
            return new StandardCalculator();
        } else {
            return new FirstVisitCalculator();
        }
    }
}
