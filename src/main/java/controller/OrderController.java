package controller;

import controller.StateManagement.StateManagement;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Model;
import model.Orders.CustomerOrder;
import model.Orders.ExtraIngredients;
import model.Orders.Order;
import model.Orders.OrderItem;
import model.pizza.Dough;
import model.pizza.Drinks.Drinks;
import model.pizza.Ingredient;
import model.pizza.Pizza;
import view.TextColor.LetterColors;

import java.util.ArrayList;
import java.util.Objects;

public class OrderController extends ControllerState{
    private OrderItem orderItem;
    private ArrayList<OrderItem> orderItems;
    private int indexOrders=0;
    private boolean usefulOptionSelected;

    public OrderController(ControllerContext context, StateManagement stateManagement) {
        super(context,stateManagement);
    }

    /**
     * In an order item there can only be one pizza and one drink, therefore if both options are already selected, we have to create
     * a new orderItem class
     */
    void checkIfCreateNeeded(){
        //option 1 with already selected pizza
        //option 2 with already selected drink
        //if both options are null.
        if((orderItems.get(indexOrders).getPizza() !=null && optionSelected ==1) || (orderItems.get(indexOrders).getDrink() !=null && optionSelected ==2) || (orderItems.get(indexOrders).getPizza() !=null && orderItems.get(indexOrders).getDrink()!=null)){
            orderItem = new OrderItem();
            orderItems.add(orderItem);
            indexOrders++;
        }
    }

    @Override
    public void showMenuAndInteract() {
        orderItem = new OrderItem();
        indexOrders=0;
        orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        //showing the current delegation in which we are making an order
        this.context.view.printToScreenColor(this.context.model.getCurrentDelegation().getName()+" is the current delegation", LetterColors.BLUE);
        usefulOptionSelected = false;
        do {
            //there is no more than 3 options to select
            showMenuAndCheckIfInbounds(3);
            doAction();
            //the user at least has selected one item in order to create an order
            if (optionSelected != 3) usefulOptionSelected =true;
        }while (optionSelected != 3);
    }

    @Override
    public void onNext() {
        //in this case we will not update the state
        if(usefulOptionSelected) {
            //thank you for your order
            this.context.view.printToScreenColor("Thank you for your order!", LetterColors.CYAN);
        } else{
            this.context.view.printToScreenColor("Making an order is not that difficult :-(", LetterColors.RED);
        }
    }

    @Override
    public void printStaticMenu() {
        //showing the options
        context.view.showOrderOptions();
    }

    @Override
    protected void doAction() {
        switch (optionSelected){
            //PIZZA CASE
            case 1:
                //we check if we have to create a new order item
                checkIfCreateNeeded();
                // getting all the PIZZA ELEMENTS
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.PIZZA, Model.getInstance().getCurrentDelegation());
                //printing the received pizzas
                Pizza selectedPizza = (Pizza) context.view.printObjectList(Model.getInstance().getAllPizzas(),"pizza");
                int pizzaQuantity = context.view.menuAskOption("How much of this product do you want?(Quantities go from 1 to 10) ",10);
                //show all DOUGHS
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.DOUGH);
                // print and select dough
                Dough dough = (Dough) context.view.printObjectList(Model.getInstance().getDoughs(),"dough");
                //asking for EXTRA INGREDIENTS
                int extraIngredientSelected =0;
                do {
                    extraIngredientSelected = this.context.view.menuAskOption(this.context.view.askExtraIngredients(), 2);
                    if (extraIngredientSelected == 1) {
                        //EXTRA INGREDIENTS SELECTION
                        Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.INGREDIENT);
                        //printing received ingredients
                        Ingredient selectedIngredient = (Ingredient) context.view.printObjectList(Model.getInstance().getIngredients(), "ingredient");
                        int ingredientQuantity = context.view.menuAskOption("How much of this product do you want?(Quantities go from 1 to 10) ", 10);
                        //adding extra ingedient
                        orderItem.addExtraIngredient(selectedIngredient, ingredientQuantity);
                    }
                }while(extraIngredientSelected !=2);
                //setting pizza
                orderItem.setPizza(selectedPizza);
                orderItem.setPizzaQuantity(pizzaQuantity);
                //Dough
                orderItem.setDough(dough);
                //Inserting
                break;
            //DRINKS CASE
            case 2:
                //we check if we have to create a new order item
                checkIfCreateNeeded();
                // getting all the drinks available in the store
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.DRINK);
                // printing all the drinks to the screen
                Drinks drinks = (Drinks) context.view.printObjectList(Model.getInstance().getDrinks(), "drink");
                int drinksQuantity = context.view.menuAskOption("How much of this product do you want?(Quantities go from 1 to 10) ",10);
                // adding drinks
                orderItem.setDrink(drinks);
                orderItem.setDrinkQuantity(drinksQuantity);
                break;
            case 3:
                //order finished
                //we make an order if the user previously selected to buy something
                if(usefulOptionSelected) {
                    //insert COrder
                    CustomerOrder customerOrder = new CustomerOrder(this.context.model.getCurrentCustomer().getCustomerId(), context.model.getCurrentDelegation());
                    Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).insertAndGetId(customerOrder, TableTypes.CUSTOMER_ORDER);
                    //get the COrder id and put it to the order class
                    this.context.model.addOrder(new Order(this.context.model.getCustomerOrder().getIdOrder()));
                    //insert all the orderItems to the database
                    for (OrderItem orderItem:
                         orderItems) {
                        ArrayList<ExtraIngredients> extraIngredients = (ArrayList<ExtraIngredients>) orderItem.getExtraIngredients().clone();
                        //removing all extra ingredients
                        orderItem.cleanExtraIngredients();
                        //adding the order id to the order item
                        orderItem.setId_order(this.context.model.getLastOrder().getId_order());
                        //inserting the order item
                        Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).insertAndGetId(orderItem, TableTypes.ORDER_ITEMS);
                        try {
                            //find the last inserted order item
                            OrderItem orderItemLast =this.context.model.getLastOrder().getOrderItems().get(this.context.model.getLastOrder().getOrderItems().size()-1);
                            for (ExtraIngredients extraIngredientsObject:
                                  extraIngredients) {
                                //adding the id order item to the extra ingredient
                                extraIngredientsObject.setId_order_item(orderItemLast.getId_order_item());
                                extraIngredientsObject.setId_order(orderItemLast.getId_order());
                                // inserting the extra ingredients
                                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).insertAndGetId(extraIngredientsObject, TableTypes.EXTRA_INGREDIENTS);

                            }
                        }catch (IndexOutOfBoundsException e){
                            e.printStackTrace();
                        }
                    }
                    //for each order item add the extra ingredient
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + optionSelected);
        }
    }
}
