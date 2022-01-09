package database.Connectors.enums;

public enum TableTypes {
    CUSTOMER("Customer");

    private final String tableType;
    TableTypes(String tableType) {
        this.tableType = tableType;
    }

    @Override
    public final String toString() {
        return tableType;
    }
}