package model.pizza.Drinks;

public enum DrinkType {
    AIGUA("Aigua"),
    REFRESC("Refresc"),
    CERVESA("Cervesa");

    private final String drinkType;

    DrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    /**
     * Function that allows us to check if the current drink selected is an adult drink or not
     * in order to ask for their age
     * @return True if is an adult drink
     * false if it is not
     */
    public boolean isAnAdultDrink(){
        return this.drinkType.equals(CERVESA.drinkType);
    }
}
