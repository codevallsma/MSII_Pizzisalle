package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Model;
import model.pizza.Dough;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDough extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        onSuccessBaseRepo(object, Model.getInstance().getDoughs(),dbMethods, TableTypes.DOUGH.toString());
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
    public PreparedStatement getById(Object object) {
        return null;
    }

    @Override
    public PreparedStatement getAll(Object object) throws SQLException {
        // no object is needed in this get all
        return MysqlConnector.getInstance().getConn().prepareStatement(
                "SELECT m.id_massa, m.name FROM Massa m;"
                , PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Object resultSetToObject(ResultSet resultSet) throws SQLException {
        return new Dough((Integer.parseInt(resultSet.getString("id_massa"))), resultSet.getString("name"));
    }
}
