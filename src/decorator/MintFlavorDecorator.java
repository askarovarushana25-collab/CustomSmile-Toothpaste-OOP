package decorator;

import model.Toothpaste;

public class MintFlavorDecorator extends ToothpasteDecorator {
    public MintFlavorDecorator(Toothpaste toothpaste) {
        super(toothpaste);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " +  Mint Blast";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 0.75;
    }

    @Override
    public String getFormulaProperties() {
        return super.getFormulaProperties() + ", Natural Menthol Cooling";
    }
}
