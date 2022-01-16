import Utils.Passwords;
import controller.ControllerContext;
import controller.StateManagement.StateManagement;
import database.Connectors.GeneralDBConnector;
import database.Connectors.enums.DBTypes;
import model.Model;
import model.pizza.Drinks.DrinkBuilder;
import model.pizza.Drinks.exception.DrinkNotFoundException;
import model.pizza.Drinks.DrinkType;
import view.View;

import java.util.Objects;

/**
 * IMPORTANT!!! EXECUTE THE DATABASE_STRUCTURE FILE ON MYSQL BEFORE EXECUTING THE JAVA MAIN
 */
public class Main {
    public static void main(String[] args) throws Exception {
        View  view = new View();
        Model model = Model.getInstance();
        // subscribing to table notifications, that tells that the information has been updated deleted or inserted
        // and updates it to the model so the database and the model in ram are always synced
        model.subscribeTables();
        // connecting database msyql
        Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.MYSQL)).connect();
        // connecting database neo4j (we do not need the neo4j database, however
        // is implemented to show that another database would be easily incorporated if needed )
        Objects.requireNonNull(GeneralDBConnector.getDB(DBTypes.NEO4J)).connect();
        //Controller that contains the context (view and the model) of the program
        // to help the states do their tasks with those corresponding classes
        ControllerContext controllerContext = new ControllerContext(view,model);
        //create the state management
        StateManagement stateManagement = new StateManagement(controllerContext);
        stateManagement.startStateManagement();
        stateManagement.runStateManagement();
    }
}
