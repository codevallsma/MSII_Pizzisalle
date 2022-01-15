package model.pizza;

import model.ModifyRamClasses;

public class Pizza implements ModifyRamClasses {
    private Integer id_pizza;
    private String pizzaName;

    // We all know that the pizza also has ingredients, and we normally would include a list of ingredients
    // however the functionality of this program does not require us to store this parameter, so we will not include it


    public Pizza(Integer id_pizza, String pizzaName) {
        this.id_pizza = id_pizza;
        this.pizzaName = pizzaName;
    }

    @Override
    public void insertID(Integer id) {
        this.id_pizza = id;
    }

    public Integer getId_pizza() {
        return id_pizza;
    }

    public String getPizzaName() {
        return pizzaName;
    }
}
