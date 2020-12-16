package org.kacper.notifier;

import org.kacper.Customer;

public class PhoneNotificationHandler extends NotificationRequestHandler {
    public PhoneNotificationHandler(NotificationRequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(NotificationRequest notificationRequest) {
        Customer customer = notificationRequest.getRental().getCustomer();

        if(customer.getPhone() != null) {
            print();
            notificationRequest.markHandled();
        } else {
            super.handleRequest(notificationRequest);
        }
    }

    @Override
    public String toString() {
        return "Sending text message to customer!";
    }
}
