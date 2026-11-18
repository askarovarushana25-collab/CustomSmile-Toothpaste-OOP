# CustomSmile Toothpaste Ordering System (5COSC018C)

## Chosen Product Type
Custom Toothpaste (Herbal, Sensitive, and Kids variants with customizable active ingredients and flavors).

## Main Features
- **Multi-Order Support:** Users can create and place multiple custom toothpaste orders in one session and view their completed order history.
- **Real-Time Notifications:** Users receive instant console notifications whenever a base product is selected, customized, undone, canceled, or purchased.
- **Undo Last Customization:** Users can step backward at any point during customization to remove the most recently added ingredient.
- **Mutually Exclusive & Duplicate Validation:** Prevents adding the same customization twice and blocks incompatible combinations (e.g., Activated Charcoal cannot be combined with Whitening; Extra Fluoride cannot be added to Kids Toothpaste).
- **Payment Simulation:** Supports Credit/Debit Card, Uzcard/Humo, and Cash on Delivery checkout options.

## Design Patterns Used
1. **Creational Pattern — Factory Method (`ToothpasteFactory`):**
    - *Why:* Encapsulates the instantiation logic for concrete base toothpaste classes (`HerbalToothpaste`, `SensitivityToothpaste`, `KidsToothpaste`), keeping the client (`Main`) decoupled from object creation details.
2. **Structural Pattern — Decorator (`ToothpasteDecorator` & subclasses):**
    - *Why:* Allows dynamic stacking of add-on ingredients (`WhiteningDecorator`, `FluorideDecorator`, `CharcoalDecorator`, `MintFlavorDecorator`) at runtime without creating an explosion of subclasses, while naturally supporting the "Undo" feature by unwrapping layers.
3. **Behavioral Pattern — Observer (`OrderObserver` & `ConsoleNotificationObserver`):**
    - *Why:* Decouples user notification delivery from core business logic inside `OrderService`, automatically alerting the user whenever the order state changes.

## Group Members & Git Log Mapping
- **Member 1:** Git Username: `askarovarushana25-collab` | Student ID: `00019912`
- **Member 2:** Git Username: `<AzizSahodatilloyev>` | Student ID: `00019814`

## Notes & Assumptions
- All product and customization prices are listed in USD ($).
- Console input is validated via a dedicated helper method (`readInt`) so invalid strings or out-of-range numbers are handled gracefully without throwing exceptions.
