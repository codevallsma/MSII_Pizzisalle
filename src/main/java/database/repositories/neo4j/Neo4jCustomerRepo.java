package database.repositories.neo4j;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.mappers.CrudOperations;
import database.repositories.BaseRepositories;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Neo4jCustomerRepo extends BaseRepositories implements CrudOperations<PreparedStatement,Object> {

    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {

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
    public Object resultSetToObject(ResultSet resultSet) throws SQLException {
        return null;
    }


}
