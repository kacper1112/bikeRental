package org.kacper.notifier;

import org.kacper.Rental;

public class NotificationRequest {
    private final Rental rental;
    private boolean handled;
    
    public NotificationRequest(final Rental rental) {
        this.rental = rental;
    }
    
    public boolean isHandled() {
        return handled;
    }
    
    public void markHandled() {
        handled = true;
    }
    
    public Rental getRental() {
        return rental;
    }
}
