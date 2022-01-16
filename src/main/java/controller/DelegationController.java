package controller;

import controller.StateManagement.ChangeStateInterface;
import model.Delegation.Delegation;
import model.Delegation.DelegationBuilder;

public class DelegationController extends ControllerState{

    public DelegationController(ControllerContext context, ChangeStateInterface changeStateInterface) {
        super(context, changeStateInterface);
    }

    @Override
    public void showMenuAndInteract() {
        //we only have 4 delegations to select
        printStaticMenu();
        showMenuAndCheckIfInbounds(4);
        doAction();
    }

    @Override
    public void onNext() {

    }

    @Override
    protected void printStaticMenu() {
        context.view.delegationMenu();
    }

    @Override
    protected void doAction() {
        this.context.model.setCurrentDelegation(DelegationBuilder.buildDelegation(optionSelected));
    }
}
