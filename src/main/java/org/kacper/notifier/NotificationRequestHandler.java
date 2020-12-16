package org.kacper.notifier;

public abstract class NotificationRequestHandler {
    private final NotificationRequestHandler next;
    
    public NotificationRequestHandler(NotificationRequestHandler next) {
        this.next = next;
    }
    
    public void handleRequest(NotificationRequest notificationRequest) {
        if(next != null) {
            next.handleRequest(notificationRequest);
        }
    }
    
    protected void print() {
        System.out.println("handling request: " + this);
    }
}
