package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Customer;
import model.Delegation.Delegation;
import model.Model;
import model.pizza.Pizza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlPizzaRepo extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        onSuccessBaseRepo(object,  Model.getInstance().getAllPizzas(),dbMethods, TableTypes.PIZZA.toString());
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        // we won't ever insert a pizza
        return null;
    }

    @Override
    public PreparedStatement delete(Object object) {
        // we won't ever delete a pizza
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
    public PreparedStatement getById(Object object) {
        return null;
    }

    /**
     * Gets the all pizzas given the delegation the customer is
     * @param object
     * @return
     * @throws SQLException
     */
    @Override
    public PreparedStatement getAll(Object object) throws SQLException{
        Delegation delegation = (Delegation) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "SELECT p.id_pizza, p.name FROM Pizza AS p WHERE p.name NOT IN ( SELECT d.name FROM Delegation AS d) OR p.name = ? ;"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString((int)1, delegation.getName());
        return preparedStatement;
    }

    @Override
    public Object resultSetToObject(ResultSet rs) throws SQLException {
        return new Pizza(Integer.parseInt(rs.getString("id_pizza")), (String)rs.getString("name"));
    }
}
