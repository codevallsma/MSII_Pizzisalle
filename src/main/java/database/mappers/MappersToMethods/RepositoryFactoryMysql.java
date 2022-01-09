package database.mappers.MappersToMethods;

import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import database.repositories.mysql.MysqlCustomerRepo;

public class RepositoryFactoryMysql extends RepositoriesFactory{
    @Override
    public BaseRepositories getRepositoryClass(TableTypes tableType) {
        switch (tableType) {
            case CUSTOMER:
                return new MysqlCustomerRepo();
        }
        return null;
    }
}
