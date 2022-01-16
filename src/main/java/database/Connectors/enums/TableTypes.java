package database.Connectors.enums;

public enum TableTypes {
    CUSTOMER("Customer"),
    PIZZA("Pizza"),
    DRINK("Drink"),
    INGREDIENT("Ingredient"),
    DOUGH("Dough"),
    CUSTOMER_ORDER("CustomerOrder"),
    ORDER_ITEMS("OrderItems"),
    EXTRA_INGREDIENTS("ExtraIngredients");

    private final String tableType;
    TableTypes(String tableType) {
        this.tableType = tableType;
    }

    @Override
    public final String toString() {
        return tableType;
    }
}
