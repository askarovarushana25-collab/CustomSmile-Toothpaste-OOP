package factory;

import model.*;

public class ToothpasteFactory {
    public static Toothpaste createToothpaste(int choice) {
        return switch (choice) {
            case 1 -> new HerbalToothpaste();
            case 2 -> new SensitivityToothpaste();
            case 3 -> new KidsToothpaste();
            default -> throw new IllegalArgumentException("Invalid toothpaste selection.");
        };
    }
}

