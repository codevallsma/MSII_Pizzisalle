package controller.StateManagement;

import controller.*;

import java.util.*;

//
public class StateManagement implements ChangeStateInterface {
    private  ControllerState currentState;
    private EnumMap<StateTypes,ControllerState> controllerStates;
    private ControllerContext controllerContext;
    private final Stack<ControllerState> stack;
    private boolean isRunning = false;

    public StateManagement(ControllerContext controllerContext) {
        this.controllerContext = controllerContext;
        controllerStates = new EnumMap<>(StateTypes.class);
        buildControllerStates();
        stack = new Stack<ControllerState>();
    }

    private void buildControllerStates(){
        ControllerState welcome = new WelcomeController(controllerContext, this);
        currentState = welcome;
        ControllerState showMenu = new MainMenuController(controllerContext, this);
        ControllerState makeOrder = new OrderController(controllerContext, this);
        ControllerState seeCart = new CartController(controllerContext, this);
        ControllerState updateCredentials = new UpdateCredentialsController(controllerContext, this);
        ControllerState goodbyeController = new GoodbyeController(controllerContext, this);
        ControllerState updateDelegation = new DelegationController(controllerContext, this);
        ControllerState printCustomerController = new PrintCustomerController(controllerContext, this);
        controllerStates.put(StateTypes.WELCOME,welcome);
        controllerStates.put(StateTypes.SHOW_MENU, showMenu);
        controllerStates.put(StateTypes.MAKE_ORDER, makeOrder);
        controllerStates.put(StateTypes.CART,seeCart);
        controllerStates.put(StateTypes.UPDATE_CREDENTIALS,updateCredentials);
        controllerStates.put(StateTypes.EXIT, goodbyeController);
        controllerStates.put(StateTypes.UPDATE_DELEGATION, updateDelegation);
        controllerStates.put(StateTypes.PRINT_CUSTOMER_INFO,printCustomerController);
    }

    @Override
    public void nextState(StateTypes stateTypes) {
        // if the stack is empty we cann update the current state to the
        if(stack.isEmpty())
        currentState = controllerStates.get(stateTypes);
    }

    @Override
    public void pushState(StateTypes stateTypes) {
        stack.push(controllerStates.get(stateTypes));
    }

    @Override
    public void startStateManagement() {
        isRunning = true;
    }

    @Override
    public void endStateManagement() {
        isRunning = false;
        currentState = controllerStates.get(StateTypes.EXIT);
    }

    @Override
    public void runStateManagement() {
        //the welcome controller is only executed once
        pushState(StateTypes.WELCOME);
        while (isRunning){
            currentState = nextState();
            currentState.showMenuAndInteract();
            currentState.onNext();
        }
        //goodbye message
       currentState.showMenuAndInteract();
    }
    private ControllerState nextState(){
        if (stack.isEmpty()) return controllerStates.get(StateTypes.SHOW_MENU);
        return stack.pop();
    }
}
