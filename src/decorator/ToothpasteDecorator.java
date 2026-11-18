package decorator;

import model.Toothpaste;

public abstract class ToothpasteDecorator implements Toothpaste {
    protected final Toothpaste wrappedToothpaste;

    public ToothpasteDecorator(Toothpaste toothpaste) {
        this.wrappedToothpaste = toothpaste;
    }

    public Toothpaste unwrap() {
        return wrappedToothpaste;
    }

    @Override
    public String getDescription() {
        return wrappedToothpaste.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedToothpaste.getPrice();
    }

    @Override
    public String getFormulaProperties() {
        return wrappedToothpaste.getFormulaProperties();
    }
}
