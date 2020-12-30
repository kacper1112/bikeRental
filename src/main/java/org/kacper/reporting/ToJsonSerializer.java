package org.kacper.reporting;

import com.google.gson.Gson;
import org.kacper.Rental;

public class ToJsonSerializer implements RentalSerializer {

    @Override
    public String serializeRental(Rental rental) {
        Gson gson = new Gson();

        return gson.toJson(rental);
    }
}
