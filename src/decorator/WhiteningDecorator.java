package decorator;

import model.Toothpaste;

public class WhiteningDecorator extends ToothpasteDecorator {
    public WhiteningDecorator(Toothpaste toothpaste) {
        super(toothpaste);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Micro-Whitening Crystals";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1.50;
    }

    @Override
    public String getFormulaProperties() {
        return super.getFormulaProperties() + ",  Polishing";
    }
}
