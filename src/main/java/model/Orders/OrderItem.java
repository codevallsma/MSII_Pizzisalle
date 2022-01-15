package model.Orders;

import model.ModifyRamClasses;
import model.pizza.Drinks.Drinks;
import model.pizza.Massa;
import model.pizza.Pizza;

import java.util.ArrayList;

public class OrderItem implements ModifyRamClasses {
    private int id_order_item;
    //only id in database
    private Pizza pizza;
    private int pizzaQuantity;
    private Massa massa;
    // extra ingredients the user will be able to ask for
    private ArrayList<ExtraIngredients> extraIngredients;
    //only id in database
    private Drinks drink;
    private Integer drinkQuantity;
    //the foreign key that refers to the total orders
    private Integer id_order;

    public OrderItem(int id_order_item, Pizza pizza, int pizzaQuantity, Massa massa ,ArrayList<ExtraIngredients> extraIngredients, Drinks drink, Integer drinkQuantity, Integer id_order) {
        this.id_order_item = id_order_item;
        this.pizza = pizza;
        this.pizzaQuantity = pizzaQuantity;
        this.massa = massa;
        this.extraIngredients = extraIngredients;
        this.drink = drink;
        this.drinkQuantity = drinkQuantity;
        this.id_order = id_order;
    }

    public int getId_order_item() {
        return id_order_item;
    }

    public void setId_order_item(int id_order_item) {
        this.id_order_item = id_order_item;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getPizzaQuantity() {
        return pizzaQuantity;
    }

    public void setPizzaQuantity(int pizzaQuantity) {
        this.pizzaQuantity = pizzaQuantity;
    }

    public ArrayList<ExtraIngredients> getExtraIngredients() {
        return extraIngredients;
    }

    public void setExtraIngredients(ArrayList<ExtraIngredients> extraIngredients) {
        this.extraIngredients = extraIngredients;
    }

    public Massa getMassa() {
        return massa;
    }

    public void setMassa(Massa massa) {
        this.massa = massa;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    public Integer getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(Integer drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    @Override
    public void insertID(Integer id) {
        this.id_order_item = id;
    }

    @Override
    public String getName() {
        return Integer.toString(id_order_item);
    }
}
