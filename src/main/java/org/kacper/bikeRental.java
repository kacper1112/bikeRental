package org.kacper;

import org.kacper.menu.InitMenu;
import org.kacper.notifier.NotificationScheduler;
import org.kacper.reporting.Reporter;
import org.kacper.reporting.ToJsonSerializer;

public class bikeRental {
    
    
    public static void main(String[] args) {
        //NotificationScheduler.start();
        //InitMenu.show();
        Reporter reporter = new Reporter(new ToJsonSerializer());
        reporter.generateReport();
    }
}
