package org.kacper;

import org.kacper.menu.InitMenu;
import org.kacper.notifier.NotificationScheduler;

public class bikeRental {
    
    
    public static void main(String[] args) {
        NotificationScheduler.start();
        InitMenu.show();
    }
}
