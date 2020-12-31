package org.kacper.reporting;

import org.kacper.Rental;

import java.util.List;

public class RentalSummaryPrinterAdapter implements RentalSerializer {
    private final RentalSummaryPrinter printer;
    
    public RentalSummaryPrinterAdapter() {
        printer = new RentalSummaryPrinter();
    }

    @Override
    public String serializeRentals(List<Rental> rentals) {
        StringBuilder result = new StringBuilder();
        
        for(Rental rental : rentals) {
            result.append(printer.printSummary(rental.getId()));
        }
        
        return result.toString();
    }
}
