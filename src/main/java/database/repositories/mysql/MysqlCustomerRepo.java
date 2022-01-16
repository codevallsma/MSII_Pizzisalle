package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.mappers.CrudOperations;
import database.repositories.BaseRepositories;
import model.Customer;
import model.Model;
import model.pizza.Pizza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlCustomerRepo extends BaseRepositories {

    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        onSuccessBaseRepo(object, Model.getInstance().getCurrentCustomer(),dbMethods, TableTypes.CUSTOMER.toString());
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        Customer customer = (Customer) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "INSERT INTO Customer (name, surname1, surname2, phone_number, address, city) VALUES (?,?,?,?,?,?);"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString((int)1,  customer.getName());
        preparedStatement.setString((int)2,  customer.getSurname1());
        preparedStatement.setString((int)3,  customer.getSurname2());
        preparedStatement.setString((int)4,  customer.getPhone_number());
        preparedStatement.setString((int)5,  customer.getAddress());
        preparedStatement.setString((int)6,  customer.getCity());
        return preparedStatement;
    }

    @Override
    public PreparedStatement delete(Object object) {
        return null;
    }

    @Override
    public PreparedStatement update(Object object) throws SQLException {
        Customer customer = (Customer) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "UPDATE Customer as c  SET c.name =?,c.surname1 =?, c.surname2 =?, c.phone_number=?, c.address=?, c.city=? WHERE c.id_customer =?;"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString((int)1,  customer.getName());
        preparedStatement.setString((int)2,  customer.getSurname1());
        preparedStatement.setString((int)3,  customer.getSurname2());
        preparedStatement.setString((int)4,  customer.getPhone_number());
        preparedStatement.setString((int)5,  customer.getAddress());
        preparedStatement.setString((int)6,  customer.getCity());
        preparedStatement.setObject((int)7,  customer.getCustomerId());
        return preparedStatement;
    }

    @Override
    public PreparedStatement get(Object object) {
        return null;
    }

    @Override
    public PreparedStatement getById(Object object) throws SQLException {
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "SELECT c.id_customer, c.name, c.surname1, c.surname2, c.phone_number, c.address, c.city from Customer as c where c.id_customer = ?"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString((int)1, (String) Integer.toString((Integer) object) );
        return preparedStatement;
    }

    @Override
    public PreparedStatement getAll(Object object) {
        return null;
    }

    @Override
    public Object resultSetToObject(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(Integer.parseInt(rs.getString("id_customer")));
        customer.setName(rs.getString("name"));
        customer.setSurname1(rs.getString("surname1"));
        customer.setSurname2(rs.getString("surname2"));
        customer.setPhone_number(rs.getString("phone_number"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        return customer;
    }

}
