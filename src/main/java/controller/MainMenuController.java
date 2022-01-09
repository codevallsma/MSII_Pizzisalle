package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateTypes;

public class MainMenuController extends ControllerState{

    public MainMenuController(ControllerContext context, ChangeStateInterface changeStateInterface) {
        super(context, changeStateInterface);
    }

    @Override
    public void showMenuAndInteract() {
        showMenuAndCheckIfInbounds(5);
        doAction();
    }

    @Override
    public void onNext() {
        switch (optionSelected){
            case 1:
                //make order
                this.changeStateInterface.pushState(StateTypes.MAKE_ORDER);
                break;
            case 2:
                // see cart
                this.changeStateInterface.pushState(StateTypes.CART);
                break;
            case 3:
                //update credentials
                this.changeStateInterface.pushState(StateTypes.UPDATE_CREDENTIALS);
                break;
            case 4:
                // UPDATE DELEGATION
                this.changeStateInterface.pushState(StateTypes.UPDATE_DELEGATION);
                break;
            case 5:
                //end the program
                this.changeStateInterface.endStateManagement();
                break;
        }
    }

    @Override
    protected void printStaticMenu() {
        this.context.view.createCustomerMenu();
    }

    @Override
    protected void doAction() {
        //in this case we do not have to perform any other action than changing the state of the program
    }
}
