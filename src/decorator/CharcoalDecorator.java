package decorator;

import model.Toothpaste;

public class CharcoalDecorator extends ToothpasteDecorator {
    public CharcoalDecorator(Toothpaste toothpaste) {
        super(toothpaste);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Activated Bamboo Charcoal";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2.00;
    }

    @Override
    public String getFormulaProperties() {
        return super.getFormulaProperties() + ", Carbon Stain Adsorption";
    }
}

