package model.Orders;

import model.ModifyRamClasses;
import model.pizza.Drinks.Drinks;
import model.pizza.Dough;
import model.pizza.Ingredient;
import model.pizza.Pizza;

import java.util.ArrayList;

public class OrderItem implements ModifyRamClasses {
    private Integer id_order_item;
    //only id in database
    private Pizza pizza;
    private Integer pizzaQuantity;
    private Dough dough;
    // extra ingredients the user will be able to ask for
    private ArrayList<ExtraIngredients> extraIngredients;
    //only id in database
    private Drinks drink;
    private Integer drinkQuantity;
    //the foreign key that refers to the total orders
    private Integer id_order;

    public OrderItem(int id_order_item, Pizza pizza, int pizzaQuantity, Dough dough, ArrayList<ExtraIngredients> extraIngredients, Drinks drink, Integer drinkQuantity, Integer id_order) {
        this.id_order_item = id_order_item;
        this.pizza = pizza;
        this.pizzaQuantity = pizzaQuantity;
        this.dough = dough;
        this.extraIngredients = extraIngredients;
        this.drink = drink;
        this.drinkQuantity = drinkQuantity;
        this.id_order = id_order;
    }

    public OrderItem() {
        extraIngredients = new ArrayList<>();
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
    public void addExtraIngredient(Ingredient extraIngredient, int ingredientQuantity){
        this.extraIngredients.add(new ExtraIngredients(extraIngredient.getId_ingredient(),ingredientQuantity));
    }
    public void cleanExtraIngredients(){
        this.extraIngredients = new ArrayList<>();
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return that.id_order_item.equals(this.id_order_item)
                && that.drink.equals((this.drink))
                && that.pizza.equals(this.pizza)
                && this.drinkQuantity.equals(that.drinkQuantity)
                &&this.pizzaQuantity.equals(that.pizzaQuantity);
    }
}
