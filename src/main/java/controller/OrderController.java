package controller;

import controller.StateManagement.StateManagement;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Model;
import model.pizza.Dough;
import model.pizza.Drinks.Drinks;
import model.pizza.Ingredient;
import model.pizza.Pizza;
import view.TextColor.LetterColors;

import java.util.Objects;

public class OrderController extends ControllerState{

    public OrderController(ControllerContext context, StateManagement stateManagement) {
        super(context,stateManagement);
    }

    @Override
    public void showMenuAndInteract() {
        //there is no more than 3 options to select
        do {
            showMenuAndCheckIfInbounds(3);
            doAction();
        }while (optionSelected != 3);
    }

    @Override
    public void onNext() {
        //thank you for your order
        this.context.view.printToScreenColor("Thank you for your order!", LetterColors.CYAN);
        //in this case we will not update the state
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
                // getting all the PIZZA ELEMENTS
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.PIZZA, Model.getInstance().getCurrentDelegation());
                //printing the received pizzas
                Pizza selectedPizza = (Pizza) context.view.printObjectList(Model.getInstance().getAllPizzas(),"pizza");
                int pizzaQuantity = context.view.askForQuantity();
                //show all DOUGHS
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.DOUGH);
                // print and select dough
                Dough dough = (Dough) context.view.printObjectList(Model.getInstance().getDoughs(),"dough");
                //asking for EXTRA INGREDIENTS
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.INGREDIENT);
                //printing received ingredients
                Ingredient selectedIngredient = (Ingredient) context.view.printObjectList(Model.getInstance().getIngredients(),"ingredient");
                int ingredientQuantity = context.view.askForQuantity();
                break;
            //DRINKS CASE
            case 2:
                // getting all the drinks available in the store
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.DRINK);
                // printing all the drinks to the screen
                Drinks drinks = (Drinks) context.view.printObjectList(Model.getInstance().getDrinks(), "drink");
                int drinksQuantity = context.view.askForQuantity();
                break;
            case 3:
                //order finished
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + optionSelected);
        }
    }
}
