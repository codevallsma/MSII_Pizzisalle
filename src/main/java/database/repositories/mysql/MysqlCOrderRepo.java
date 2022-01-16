package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Customer;
import model.Delegation.DelegationBuilder;
import model.Model;
import model.Orders.CustomerOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MysqlCOrderRepo extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        onSuccessBaseRepo(object, Model.getInstance().getCustomerOrder(),dbMethods, TableTypes.CUSTOMER_ORDER.toString());
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        CustomerOrder customerOrder = (CustomerOrder) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "INSERT INTO COrder (id_customer, id_delegation) VALUES (?,?);"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt((int)1,  customerOrder.getIdCustomer());
        preparedStatement.setInt((int)2,  customerOrder.getDelegation().getId());
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
        try {
            return new CustomerOrder(Integer.parseInt(resultSet.getString("id_order")),
                    Integer.parseInt(resultSet.getString("id_customer")),
                    DelegationBuilder.buildDelegation(Integer.parseInt(resultSet.getString("id_delegation"))),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CustomerOrder();
    }
}
