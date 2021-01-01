package org.kacper.reporting;


import org.kacper.Rental;
import org.kacper.repo.RentalDataMapper;

public class RentalSummaryPrinter {
    public String printSummary(int rentalId) {
        Rental rental = RentalDataMapper.getInstance().getRentalById(rentalId);
        
        String result = '\n' + Integer.toString(rental.getId()) + '\n' +
                rental.getFrom().toString() + '\n' +
                rental.getTo() + '\n' +
                rental.getCustomer().toString() +
                rental.getRentalItems().toString() + '\n' +
                rental.getPriceCalculator().getName() + '\n';
        
        return result;
    }
}
