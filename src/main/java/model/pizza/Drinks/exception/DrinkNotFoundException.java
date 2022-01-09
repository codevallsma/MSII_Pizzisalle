package model.pizza.Drinks.exception;

public class DrinkNotFoundException extends Exception{
    public DrinkNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
