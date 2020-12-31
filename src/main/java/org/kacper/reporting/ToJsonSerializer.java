package org.kacper.reporting;

import com.google.gson.Gson;
import org.kacper.Rental;

import java.util.List;

public class ToJsonSerializer implements RentalSerializer {

    @Override
    public String serializeRentals(List<Rental> rental) {
        Gson gson = new Gson();

        return gson.toJson(rental);
    }
}
