package controller;

import controller.StateManagement.StateManagement;
import controller.StateManagement.StateTypes;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Customer;
import model.Model;
import view.TextColor.LetterColors;

public class UpdateCredentialsController extends ControllerState{
    private Customer customer;
    public UpdateCredentialsController(ControllerContext context, StateManagement stateManagement) {
        super(context,stateManagement);
    }

    @Override
    public void showMenuAndInteract() {
        printStaticMenu();
        doAction();
    }

    @Override
    public void onNext() {
        this.changeStateInterface.pushState(StateTypes.PRINT_CUSTOMER_INFO);
    }

    @Override
    public void printStaticMenu() {
        this.context.view.printToScreen("**** User Credentials Form ****");
        customer = new Customer();
        customer.setName( this.context.view.printAndScanColor("Name: ", LetterColors.RED));
        customer.setSurname1(this.context.view.printAndScanColor("Surname: ",LetterColors.RED));
        customer.setSurname2(this.context.view.printAndScanColor("Last Surname: ", LetterColors.RED));
        customer.setPhone_number(this.context.view.printAndScanColor("Introduce your phone number",LetterColors.RED));
        customer.setAddress(this.context.view.printAndScanColor("Address: ",LetterColors.RED));
        customer.setCity(this.context.view.printAndScanColor("City: ",LetterColors.RED));
    }

    @Override
    protected void doAction() {
        //check if the user currently
        GeneralDBConnector.getDB(DBTypes.MYSQL).insertAndGetId(customer, TableTypes.CUSTOMER);

    }
}
