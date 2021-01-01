package org.kacper.reporting;

import org.kacper.Rental;
import org.kacper.repo.RentalDataMapper;

import java.util.List;

public class RentalReporter {
    private final RentalSerializer serializer;

    public RentalReporter(RentalSerializer serializer) {
        this.serializer = serializer;
    }

    public void generateReport() {
        List<Rental> rentals = RentalDataMapper.getInstance().getAllRentals();

        String serialized = serializer.serializeRentals(rentals);

        System.out.println(serialized);
    }
}
