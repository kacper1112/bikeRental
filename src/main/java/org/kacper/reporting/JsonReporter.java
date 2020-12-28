package org.kacper.reporting;

import com.google.gson.Gson;
import org.kacper.Rental;
import org.kacper.repo.RepoGetOperation;

import java.util.List;

public class JsonReporter implements IReporter {
    private final List<Rental> rentals;

    public JsonReporter() {
        rentals = RepoGetOperation.getInstance().getAllRentals();
    }

    public void generateReport() {
        Gson gson = new Gson();

        System.out.println(gson.toJson(rentals));
    }
}
