package org.kacper;


import org.kacper.repo.RepoGetOperation;
import org.kacper.reporting.RentalReporter;
import org.kacper.reporting.RentalSummaryPrinter;
import org.kacper.reporting.RentalSummaryPrinterAdapter;
import org.kacper.reporting.ToJsonSerializer;

public class bikeRental {
    
    
    public static void main(String[] args) {
        //NotificationScheduler.start();
        //InitMenu.show();
        RentalReporter reporter = new RentalReporter(new RentalSummaryPrinterAdapter());
        reporter.generateReport();
    }
}
