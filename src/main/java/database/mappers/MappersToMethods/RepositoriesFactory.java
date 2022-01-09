package database.mappers.MappersToMethods;

import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import database.repositories.mysql.MysqlCustomerRepo;

import java.util.EnumMap;

//FLYWEIGHT METHOD
// We apply the FLYWEIGHT method in order to save RAM, because imagine the memory usage we would use if everytime we
// want to make a query we had to create the corresponding repository

public abstract class RepositoriesFactory {
    private final EnumMap<TableTypes, BaseRepositories> repositories;

    public RepositoriesFactory() {
        repositories = new EnumMap<TableTypes, BaseRepositories>(TableTypes.class);
    }

    /**
     * This method ensures that the repositories are only created once to save RAM
     * @param tableType
     * @return
     */
    public BaseRepositories getRepository(TableTypes tableType) {
        if(repositories.get(tableType) != null){
            return repositories.get(tableType);
        } else{
            //getting the corresponding repository according to the database and table that we are using
            BaseRepositories baseRepositories = getRepositoryClass(tableType);
            repositories.put(tableType, baseRepositories);
            return baseRepositories;
        }
    }
    protected abstract BaseRepositories getRepositoryClass(TableTypes tableType);
}
