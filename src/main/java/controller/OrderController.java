package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateManagement;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Model;
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
            case 1:
                // getting all the pizza elements
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getAll(TableTypes.PIZZA, Model.getInstance().getCurrentDelegation());
                //printing the received pizzas
                context.view.printPizzas(Model.getInstance().getAllPizzas());
                break;
            case 2:
                // drink
                break;
            case 3:
                //order finished
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + optionSelected);
        }
    }
}
