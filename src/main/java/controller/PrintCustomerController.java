package controller;

import controller.StateManagement.ChangeStateInterface;

public class PrintCustomerController extends ControllerState{

    public PrintCustomerController(ControllerContext context, ChangeStateInterface changeStateInterface) {
        super(context, changeStateInterface);
    }

    @Override
    public void showMenuAndInteract() {
        this.context.view.printUserInformation(this.context.model.getCurrentCustomer(),  this.context.model.getCurrentDelegation().getName());
    }

    @Override
    public void onNext() {

    }

    @Override
    protected void printStaticMenu() {

    }

    @Override
    protected void doAction() {

    }
}
