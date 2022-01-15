package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Model;
import model.pizza.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlIngredientRepo extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        onSuccessBaseRepo(object, Model.getInstance().getIngredients(),dbMethods, TableTypes.INGREDIENT.toString());
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
                "SELECT i.id_Ingredient, i.name FROM Ingredient i;"
                , PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Object resultSetToObject(ResultSet resultSet) throws SQLException {
        return new Ingredient((Integer.parseInt(resultSet.getString("id_Ingredient"))), resultSet.getString("name"));
    }
}
