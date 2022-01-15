package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateTypes;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Customer;
import view.TextColor.LetterColors;

import java.util.Objects;

public class WelcomeController extends ControllerState {

    private int userInput;

    public WelcomeController(ControllerContext context, ChangeStateInterface changeStateInterface) {
        super(context, changeStateInterface);
    }

    @Override
    public void showMenuAndInteract() {
        //in the welcome section we do not need any interaction to select the menu
        printStaticMenu();
        doAction();
    }

    @Override
    public void onNext() {
        // the next step is the main menu, but it is the default current state when the stack is empty

        //if the user already exists does not ask to fill the user form and show all menu

        //if the user does not exist, we change this state to the one that updates credentials (we push the current state)
        if(userInput ==2)
        this.changeStateInterface.pushState(StateTypes.UPDATE_CREDENTIALS);
        else this.changeStateInterface.pushState(StateTypes.PRINT_CUSTOMER_INFO);
    }

    @Override
    public void printStaticMenu() {
        //check if user exists
        //if it does not
        this.context.view.showWelcomeText();
        userInput =this.context.view.menuAskOption(this.context.view.createDoYouHaveUsername(),2);
    }

    @Override
    protected void doAction() {
        //we search for the given user to see if it already exists
        if(userInput ==1){
            Customer customer = null;
            boolean loop = false;
            do {
                if(loop){
                    System.out.println();
                    this.context.view.printToScreenColor("Error! User not found!! Try again\n", LetterColors.RED);
                }
                int customerId = Integer.parseInt(this.context.view.askForCustomerId());
                Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).getById(customerId, TableTypes.CUSTOMER);
                customer = context.model.getCurrentCustomer();
                loop = true;
            }while (customer==null);
        }
    }
}
