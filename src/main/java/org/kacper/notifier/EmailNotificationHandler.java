package org.kacper.notifier;

import org.kacper.Customer;

public class EmailNotificationHandler extends NotificationRequestHandler {
    public EmailNotificationHandler(NotificationRequestHandler next) {
        super(next);
    }
    
    @Override
    public void handleRequest(NotificationRequest notificationRequest) {
        Customer customer = notificationRequest.getRental().getCustomer();
        
        if(customer.getEmail() != null) {
            print();
            notificationRequest.markHandled();
        } else {
            super.handleRequest(notificationRequest);
        }
    }
    
    @Override
    public String toString() {
        return "Sending email to customer!";
    }
}
