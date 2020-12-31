package org.kacper.reporting;

import org.kacper.Rental;

import java.util.List;

public interface RentalSerializer {
    String serializeRentals(List<Rental> rental);
}
