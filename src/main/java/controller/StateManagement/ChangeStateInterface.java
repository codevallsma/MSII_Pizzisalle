package controller.StateManagement;

import controller.ControllerState;

public interface ChangeStateInterface {
    void nextState(StateTypes stateTypes);
    void pushState(StateTypes stateTypes);
    void startStateManagement();
    void endStateManagement();
    void runStateManagement();
}
