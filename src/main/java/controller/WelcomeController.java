package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateTypes;

public class WelcomeController extends ControllerState {

    private String userInput;

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
        //this.changeStateInterface.pushState(StateTypes.UPDATE_CREDENTIALS);
    }

    @Override
    public void printStaticMenu() {
        //check if user exists
        //if it does not
        this.context.view.showWelcomeText();
        userInput = this.context.view.askForUsername();
    }

    @Override
    protected void doAction() {
        //we search for the given user to see if it already exists

    }
}
