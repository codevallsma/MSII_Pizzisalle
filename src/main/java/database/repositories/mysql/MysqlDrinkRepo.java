package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Delegation.Delegation;
import model.Model;
import model.pizza.Drinks.Drinks;
import model.pizza.Drinks.exception.DrinkNotFoundException;
import model.pizza.Pizza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDrinkRepo  extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        try{
            Object newObject = null;
            switch (dbMethods){
                case INSERT: case UPDATE: case GET_ALL:
                    // we just inserted this object in the database
                    newObject = object;
                    break;
                case GET:
                    newObject = resultSetToObject((ResultSet) object);
                    break;
                case DELETE:
                    // to delete the whole object we will replace the current object for one that it is null
                    // however it is already assigned
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dbMethods);
            }
            this.observer.firePropertyChange(TableTypes.DRINK.toString(), Model.getInstance().getDrinks(), newObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement delete(Object object) {
        return null;
    }

    @Override
    public PreparedStatement update(Object object) {
        return null;
    }

    @Override
    public PreparedStatement get(Object object) {
        return null;
    }

    @Override
    public PreparedStatement getAll(Object object) throws SQLException {
        // no object is needed in this get all
        return MysqlConnector.getInstance().getConn().prepareStatement(
                "SELECT d.id_drink, d.name FROM Drink AS d;"
                , PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Object resultSetToObject(ResultSet resultSet) throws SQLException {
        try {
            return new Drinks(Integer.parseInt(resultSet.getString("id_drink")), resultSet.getString("name"));
        } catch (DrinkNotFoundException e) {
            e.printStackTrace();
            this.onFailure(e);
        }
        return new Drinks();
    }
}
