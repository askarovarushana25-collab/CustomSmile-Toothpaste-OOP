package service;

import decorator.*;
import model.Toothpaste;
import observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private Toothpaste currentToothpaste;
    private final List<OrderObserver> observers = new ArrayList<>();
    private final List<Toothpaste> completedOrders = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }

    public void startNewOrder(Toothpaste base) {
        this.currentToothpaste = base;
        notifyObservers(String.format("Selected: %s\nFormula: [%s] | Price: $%.2f",
                currentToothpaste.getDescription(),
                currentToothpaste.getFormulaProperties(),
                currentToothpaste.getPrice()));
    }

    public void applyCustomization(int option) {
        if (currentToothpaste == null) return;
        String desc = currentToothpaste.getDescription();

        // Prevent duplicate customizations
        if ((option == 1 && desc.contains("Micro-Whitening Crystals")) ||
                (option == 2 && desc.contains("Extra Fluoride Protection")) ||
                (option == 3 && desc.contains("Activated Bamboo Charcoal")) ||
                (option == 4 && desc.contains(" Mint Blast"))) {
            notifyObservers("Action blocked: This customization is already applied to your toothpaste.");
            return;
        }

        // Mutually exclusive business rules
        if (option == 1 && desc.contains("Charcoal")) {
            notifyObservers("Action blocked: Micro-Whitening Crystals cannot be combined with Activated Charcoal.");
            return;
        }
        if (option == 3 && desc.contains("Micro-Whitening")) {
            notifyObservers("Action blocked: Activated Charcoal cannot be combined with Micro-Whitening Crystals.");
            return;
        }
        if (option == 2 && desc.contains("Kids")) {
            notifyObservers("Action blocked: Extra Fluoride Protection cannot be added to Kids Toothpaste.");
            return;
        }

        currentToothpaste = switch (option) {
            case 1 -> new WhiteningDecorator(currentToothpaste);
            case 2 -> new FluorideDecorator(currentToothpaste);
            case 3 -> new CharcoalDecorator(currentToothpaste);
            case 4 -> new MintFlavorDecorator(currentToothpaste);
            default -> currentToothpaste;
        };

        notifyObservers(String.format("Customization Applied!\nItem: %s\nFormula: [%s]\nTotal Price: $%.2f",
                currentToothpaste.getDescription(),
                currentToothpaste.getFormulaProperties(),
                currentToothpaste.getPrice()));
    }

    public void undoLastCustomization() {
        if (currentToothpaste instanceof ToothpasteDecorator decorator) {
            currentToothpaste = decorator.unwrap();
            notifyObservers(String.format("Last customization undone!\nReverted to: %s\nFormula: [%s]\nPrice: $%.2f",
                    currentToothpaste.getDescription(),
                    currentToothpaste.getFormulaProperties(),
                    currentToothpaste.getPrice()));
        } else {
            notifyObservers("Nothing to undo: You are currently at the base toothpaste.");
        }
    }

    public void checkout(String paymentMethod) {
        if (currentToothpaste == null) return;
        completedOrders.add(currentToothpaste);
        notifyObservers(String.format("Order Placed Successfully! Paid $%.2f via %s.\nFinal Product: %s\nFormula: [%s]",
                currentToothpaste.getPrice(),
                paymentMethod,
                currentToothpaste.getDescription(),
                currentToothpaste.getFormulaProperties()));
        currentToothpaste = null;
    }

    public void cancelOrder() {
        currentToothpaste = null;
        notifyObservers("Order cancelled.");
    }

    public List<Toothpaste> getCompletedOrders() {
        return completedOrders;
    }
}

