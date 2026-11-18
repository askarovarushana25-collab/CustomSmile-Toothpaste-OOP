package model;

public abstract class BaseToothpaste implements Toothpaste {
    private final String name;
    private final double basePrice;
    private final String baseFormula;

    public BaseToothpaste(String name, double basePrice, String baseFormula) {
        this.name = name;
        this.basePrice = basePrice;
        this.baseFormula = baseFormula;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return basePrice;
    }

    @Override
    public String getFormulaProperties() {
        return baseFormula;
    }
}

