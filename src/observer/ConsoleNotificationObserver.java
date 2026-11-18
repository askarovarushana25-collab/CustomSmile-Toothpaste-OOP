package observer;

public class ConsoleNotificationObserver implements OrderObserver {
    @Override
    public void update(String message) {
        System.out.println("\n[NOTIFICATION] " + message);
    }
}

