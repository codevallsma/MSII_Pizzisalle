package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateManagement;
import view.TextColor.LetterColors;

public class OrderController extends ControllerState{

    public OrderController(ControllerContext context, StateManagement stateManagement) {
        super(context,stateManagement);
    }

    @Override
    public void showMenuAndInteract() {
        //there is no more than 3 options to select
        showMenuAndCheckIfInbounds(3);
        doAction();
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
                //pizza
                break;
            case 2:
                // drink
                break;
            case 3:
                //order finished
                break;
        }
    }
}
