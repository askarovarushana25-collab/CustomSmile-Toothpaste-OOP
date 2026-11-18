import factory.ToothpasteFactory;
import model.Toothpaste;
import observer.ConsoleNotificationObserver;
import service.OrderService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.addObserver(new ConsoleNotificationObserver());

        System.out.println("=== Welcome to CustomSmile Toothpaste Studio ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create a Custom Toothpaste Order");
            System.out.println("2. View Completed Orders (" + orderService.getCompletedOrders().size() + ")");
            System.out.println("3. Exit");
            int mainChoice = readInt("Choose an option: ", 1, 3);

            switch (mainChoice) {
                case 1 -> handleOrderFlow(orderService);
                case 2 -> displayCompletedOrders(orderService);
                case 3 -> {
                    System.out.println("Thank you for visiting CustomSmile Studio. Goodbye!");
                    running = false;
                }
            }
        }
        scanner.close();
    }

    private static void handleOrderFlow(OrderService orderService) {
        System.out.println("\nSelect Base Toothpaste:");
        System.out.println("1. Herbal Toothpaste ($4.50)");
        System.out.println("2. Sensitive Toothpaste ($5.50)");
        System.out.println("3. Kids Toothpaste ($3.50)");
        int baseChoice = readInt("Enter choice (1-3): ", 1, 3);

        Toothpaste base = ToothpasteFactory.createToothpaste(baseChoice);
        orderService.startNewOrder(base);

        boolean customizing = true;
        while (customizing) {
            System.out.println("\n--- CUSTOMIZE YOUR TOOTHPASTE ---");
            System.out.println("1. Add Micro-Whitening Crystals (+$1.50)");
            System.out.println("2. Add Extra Fluoride Protection (+$1.00)");
            System.out.println("3. Add Activated Bamboo Charcoal (+$2.00)");
            System.out.println("4. Add Mint Blast (+$0.75)");
            System.out.println("5. Undo Last Customization");
            System.out.println("6. Proceed to Checkout");
            System.out.println("7. Cancel Order");

            int action = readInt("Select an option (1-7): ", 1, 7);

            switch (action) {
                case 1, 2, 3, 4 -> orderService.applyCustomization(action);
                case 5 -> orderService.undoLastCustomization();
                case 6 -> {
                    handlePayment(orderService);
                    customizing = false;
                }
                case 7 -> {
                    orderService.cancelOrder();
                    customizing = false;
                }
            }
        }
    }

    private static void handlePayment(OrderService orderService) {
        System.out.println("\n--- PAYMENT SIMULATION ---");
        System.out.println("1. Credit / Debit Card");
        System.out.println("2. Uzcard / Humo");
        System.out.println("3. Cash on Delivery");
        int payChoice = readInt("Select payment method (1-3): ", 1, 3);

        String method = switch (payChoice) {
            case 1 -> "Credit/Debit Card";
            case 2 -> "Uzcard/Humo";
            default -> "Cash on Delivery";
        };
        orderService.checkout(method);
    }

    private static void displayCompletedOrders(OrderService orderService) {
        System.out.println("\n--- COMPLETED ORDERS ---");
        if (orderService.getCompletedOrders().isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        int i = 1;
        for (Toothpaste item : orderService.getCompletedOrders()) {
            System.out.printf("%d. %s - $%.2f%n", i++, item.getDescription(), item.getPrice());
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

