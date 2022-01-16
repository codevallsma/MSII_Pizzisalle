package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateManagement;
import view.TextColor.LetterColors;

/**
 * This class it is not required but it will be implemented in the future if we have time
 */
public class CartController extends ControllerState{

    public CartController(ControllerContext context, StateManagement stateManagement) {
        super(context, stateManagement);
    }

    @Override
    public void showMenuAndInteract() {
        // in this option we only need to show the cart information, so we do not need any interaction
        printStaticMenu();
    }

    @Override
    public void onNext() {
        //nothing to do here
    }

    @Override
    protected void printStaticMenu() {
        context.view.printToScreenColor("*********** Printing cart information **********", LetterColors.CYAN);
        doAction();
        context.view.printToScreenColor("************************************************", LetterColors.CYAN);
    }

    @Override
    protected void doAction() {
        this.context.view.printCartInfo(this.context.model.getOrders());
    }
}
