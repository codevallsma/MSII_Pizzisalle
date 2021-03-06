package database.mappers.MappersToMethods;

import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import database.repositories.mysql.*;

public class RepositoryFactoryMysql extends RepositoriesFactory{
    @Override
    public BaseRepositories getRepositoryClass(TableTypes tableType) {
        switch (tableType) {
            case CUSTOMER:
                return new MysqlCustomerRepo();
            case PIZZA:
                return new MysqlPizzaRepo();
            case DRINK:
                return new MysqlDrinkRepo();
            case INGREDIENT:
                return new MysqlIngredientRepo();
            case DOUGH:
                return new MysqlDough();
            case CUSTOMER_ORDER:
                return new MysqlCOrderRepo();
            case ORDER_ITEMS:
                return new MysqlOrderItemRepo();
            case EXTRA_INGREDIENTS:
                return new MysqlExtraIngredientRepo();
        }
        return null;
    }
}
