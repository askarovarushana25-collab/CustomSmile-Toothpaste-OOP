
package decorator;

import model.Toothpaste;

public class FluorideDecorator extends ToothpasteDecorator {
    public FluorideDecorator(Toothpaste toothpaste) {
        super(toothpaste);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Extra Fluoride Protection";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.00;
    }

    @Override
    public String getFormulaProperties() {
        return super.getFormulaProperties() + ", 1450m  Fluoride";
    }
}
