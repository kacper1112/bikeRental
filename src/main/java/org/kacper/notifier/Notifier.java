package org.kacper.notifier;

public class Notifier {
    NotificationRequestHandler chain;
    
    public Notifier() {
        buildChain();
    }
    
    private void buildChain() {
        chain = new EmailNotificationHandler(new PhoneNotificationHandler(new ManualNotificationHandler(null)));
    }
    
    public void makeRequest(NotificationRequest notificationRequest) {
        chain.handleRequest(notificationRequest);
    }
}
