package database.mappers.MappersToMethods;

import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import database.repositories.mysql.MysqlCustomerRepo;
import database.repositories.mysql.MysqlDrinkRepo;
import database.repositories.mysql.MysqlPizzaRepo;

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
        }
        return null;
    }
}
