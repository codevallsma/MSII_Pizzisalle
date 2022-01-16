package database.repositories.mysql;

import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import model.Model;
import model.Orders.ExtraIngredients;
import model.Orders.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlExtraIngredientRepo extends BaseRepositories {
    @Override
    public void onSuccess(Object object, DBMethods dbMethods) {
        ExtraIngredients extraIngredient = (ExtraIngredients) object;
        // to search for the old value we have to look through the list of orders and find the order that belongs this extra ingredient
        // inside the order we have to look for the matching id_order_item of belonging to this extraingredient
        ExtraIngredients oldValue = Model.getInstance().findExtraIngredientByIds(extraIngredient.getId_order(), extraIngredient.getId_order_item(),extraIngredient.getId_ingredient());
        //updating the model database
        onSuccessBaseRepo(object, oldValue,dbMethods, TableTypes.EXTRA_INGREDIENTS.toString());
    }

    @Override
    public void onFailure(Object object) {

    }

    @Override
    public PreparedStatement insert(Object object) throws SQLException {
        ExtraIngredients extraIngredient = (ExtraIngredients) object;
        PreparedStatement preparedStatement = MysqlConnector.getInstance().getConn().prepareStatement(
                "INSERT INTO ExtraIngredients (id_order_item, id_Ingredient, quantity) VALUES (?,?,?);"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,  extraIngredient.getId_order_item());
        preparedStatement.setObject(2,  extraIngredient.getId_ingredient());
        preparedStatement.setObject(3,  extraIngredient.getQuantity());
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
