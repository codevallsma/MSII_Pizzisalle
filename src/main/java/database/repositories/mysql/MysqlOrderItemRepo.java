package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Model;
import model.Orders.CustomerOrder;
import model.Orders.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlOrderItemRepo extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        OrderItem orderItem = ((OrderItem)object);
        onSuccessBaseRepo(object, Model.getInstance().findOrderItemById(orderItem.getId_order(), orderItem.getId_order_item()),dbMethods, TableTypes.ORDER_ITEMS.toString());
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        OrderItem orderItem = (OrderItem) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "INSERT INTO OrderItem (id_pizza, pizza_quantity, id_massa, id_drink, drink_quantity, id_order) VALUES (?,?,?,?,?,?);"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,  orderItem.getPizza().getId_pizza());
        preparedStatement.setInt((int)2,  orderItem.getPizzaQuantity());
        preparedStatement.setInt((int)3,  orderItem.getDough().getId_massa());
        preparedStatement.setInt((int)4,  orderItem.getDrink().getId_drink());
        preparedStatement.setInt((int)5,  orderItem.getDrinkQuantity());
        preparedStatement.setInt(6,  orderItem.getId_order());
        return preparedStatement;
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
    public PreparedStatement getById(Object object) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getAll(Object object) throws SQLException {
        return null;
    }

    @Override
    public Object resultSetToObject(ResultSet resultSet) throws SQLException {
        return null;
    }
}
