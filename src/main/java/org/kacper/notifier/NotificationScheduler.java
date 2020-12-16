package org.kacper.notifier;

import org.kacper.Rental;
import org.kacper.repo.RepoGetOperation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationScheduler {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public static void start() {
        scheduler.scheduleAtFixedRate(checkAndSendNotifications, 0, 30, TimeUnit.SECONDS);
    }
    
    
    private static final Runnable checkAndSendNotifications = () -> {
        List<Rental> rentals = RepoGetOperation.getInstance().getAllRentals();
        
        for(Rental rental : rentals) {
            if(rental.getTo().isAfter(LocalDateTime.now()) &&
                    ChronoUnit.MINUTES.between(rental.getTo(), LocalDateTime.now()) < 30) {
                Notifier notifier = new Notifier();
                notifier.makeRequest(new NotificationRequest(rental));
            }
        }
    };
}
