package org.kacper.calc;

import org.kacper.Rental;
import org.kacper.repo.RepoGetOperation;

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
