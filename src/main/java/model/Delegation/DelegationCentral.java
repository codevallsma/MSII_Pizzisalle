package model.Delegation;

public enum DelegationCentral {
    BARCELONA("Barcelona"),
    TARRAGONA("Tarragona"),
    LLEIDA("Lleida"),
    GIRONA("Girona");

    private String delegation;

    DelegationCentral(String delegation) {
        this.delegation = delegation;
    }

    @Override
    public String toString() {
        return this.delegation;
    }
}
