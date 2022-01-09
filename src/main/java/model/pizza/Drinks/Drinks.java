package model.pizza.Drinks;

import model.ModifyRamClasses;
import model.pizza.Drinks.exception.DrinkNotFoundException;

public class Drinks implements ModifyRamClasses {
    private Integer id_drink;
    private DrinkType drink;

    public Drinks(Integer id_drink, String drink) throws DrinkNotFoundException {
        this.id_drink = id_drink;
        this.drink = DrinkBuilder.drinkBuilder(drink);
    }

    public Drinks(Integer id_drink, DrinkType drink) {
        this.id_drink = id_drink;
        this.drink = drink;
    }

    public Drinks(DrinkType drink) {
        this.drink = drink;
    }

    public Drinks(String drink) throws DrinkNotFoundException {
        this.drink = DrinkBuilder.drinkBuilder(drink);
    }

    public Integer getId_drink() {
        return id_drink;
    }

    public void setId_drink(Integer id_drink) {
        this.id_drink = id_drink;
    }

    public DrinkType getDrink() {
        return drink;
    }

    public void setDrink(String drink) throws DrinkNotFoundException {
        this.drink = DrinkBuilder.drinkBuilder(drink);
    }

    @Override
    public void insertID(Integer id) {
        this.id_drink = id;
    }
}
