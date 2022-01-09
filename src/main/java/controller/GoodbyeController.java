package controller;

import controller.StateManagement.ChangeStateInterface;
import controller.StateManagement.StateManagement;

public class GoodbyeController extends ControllerState{

    public GoodbyeController(ControllerContext controllerContext, StateManagement stateManagement) {
        super(controllerContext, stateManagement);
    }

    @Override
    public void showMenuAndInteract() {

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
