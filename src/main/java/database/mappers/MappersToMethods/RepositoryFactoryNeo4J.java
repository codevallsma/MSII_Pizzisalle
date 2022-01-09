package database.mappers.MappersToMethods;

import database.Connectors.enums.TableTypes;
import database.repositories.BaseRepositories;
import database.repositories.mysql.MysqlCustomerRepo;
import database.repositories.neo4j.Neo4jCustomerRepo;

public class RepositoryFactoryNeo4J extends RepositoriesFactory{
    @Override
    public BaseRepositories getRepositoryClass(TableTypes tableType) {
        switch (tableType) {
            case CUSTOMER:
                return new Neo4jCustomerRepo();
        }
        return null;
    }
}