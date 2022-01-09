package controller;

import controller.StateManagement.StateManagement;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Customer;
import model.Model;
import view.TextColor.LetterColors;

public class UpdateCredentialsController extends ControllerState{
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

    }

    @Override
    public void printStaticMenu() {
        this.context.view.printToScreen("**** User Credentials Form ****");
        context.model.getCurrentCustomer().setName( this.context.view.printAndScanColor("Name: ", LetterColors.RED));
        context.model.getCurrentCustomer().setSurname1(this.context.view.printAndScanColor("Surname: ",LetterColors.RED));
        context.model.getCurrentCustomer().setSurname2(this.context.view.printAndScanColor("Last Surname: ", LetterColors.RED));
        context.model.getCurrentCustomer().setPhone_number(this.context.view.printAndScanColor("Introduce your phone number",LetterColors.RED));
        context.model.getCurrentCustomer().setAddress(this.context.view.printAndScanColor("Address: ",LetterColors.RED));
        context.model.getCurrentCustomer().setCity(this.context.view.printAndScanColor("City: ",LetterColors.RED));
    }

    @Override
    protected void doAction() {
        //check if the user currently
        GeneralDBConnector.getDB(DBTypes.MYSQL).insertAndGetId(context.model.getCurrentCustomer(), TableTypes.CUSTOMER);

    }
}
