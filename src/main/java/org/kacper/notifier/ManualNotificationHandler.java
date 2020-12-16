package org.kacper.notifier;

public class ManualNotificationHandler extends NotificationRequestHandler {
    public ManualNotificationHandler(NotificationRequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(NotificationRequest notificationRequest) {
        print();
        notificationRequest.markHandled();
    }

    @Override
    public String toString() {
        return "Klient nie podał ani numeru telefonu ani maila! Zbliża się koniec okresu wypożyczenia! Zrób coś!!";
    }
}
