package controller;

import controller.StateManagement.ChangeStateInterface;
//This is kind of an strategy pattern, however we are not using an interface to implement
// we are using abstract methods but the goal is the same
public abstract class ControllerState {
    protected ControllerContext context;
    protected boolean done;
    protected int optionSelected;
    protected ChangeStateInterface changeStateInterface;
    public abstract void showMenuAndInteract();
    public abstract void onNext();
    protected abstract void printStaticMenu();
    protected abstract void doAction();

    public void showMenuAndCheckIfInbounds(int end){
        optionSelected = 1;
        boolean secondOrMore = false;
        do {
            if(secondOrMore)context.view.errorMessageMenu(end);
            this.printStaticMenu();
            try {
                optionSelected = Integer.parseInt(this.context.view.getUserInput().nextLine());
            }catch (NumberFormatException exception){
                optionSelected+=end;
            }
            secondOrMore = true;
        } while(optionSelected< 1 || optionSelected > end);
    }

    public ControllerState(ControllerContext context, ChangeStateInterface changeStateInterface) {
        this.context = context;
        done = false;
        this.changeStateInterface = changeStateInterface;
    }
}
