package org.kacper.reporting;

import org.kacper.Rental;
import org.kacper.repo.RepoGetOperation;

import java.util.List;

public class Reporter {
    private final RentalSerializer serializer;

    public Reporter(RentalSerializer serializer) {
        this.serializer = serializer;
    }

    public void generateReport() {
        List<Rental> rentals = RepoGetOperation.getInstance().getAllRentals();

        StringBuilder report = new StringBuilder();
        for(Rental rental: rentals) {
           report.append(serializer.serializeRental(rental));
        }

        System.out.println(report);
    }
}
