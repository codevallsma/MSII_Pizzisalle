package database.mappers;


import database.Connectors.Node4JConnector;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import database.mappers.MappersToMethods.RepositoryFactoryMysql;
import database.mappers.MappersToMethods.RepositoriesFactory;
import database.mappers.MappersToMethods.RepositoryFactoryNeo4J;
import database.repositories.BaseRepositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * This class maps the given method to the function corresponding to the repository of that table
 * SINGLETON
 */
public class MapMethodToFunction {
        private final RepositoriesFactory mysqlRepositoryFactory;
        private final RepositoriesFactory neo4jRepositoryFactory;

        private MapMethodToFunction() {
            mysqlRepositoryFactory = new RepositoryFactoryMysql();
            neo4jRepositoryFactory = new RepositoryFactoryNeo4J();
        }

        private static final class InstanceHolder {
            private static final MapMethodToFunction instance = new MapMethodToFunction();
        }

        public static MapMethodToFunction getInstance() {
            return MapMethodToFunction.InstanceHolder.instance;
        }

        public PreparedStatement entityToQuery(DBTypes dbTypes, DBMethods action, TableTypes tableType, Object object) throws SQLException {
            switch (dbTypes) {
                case MYSQL:
                    return entityAction(action,mysqlRepositoryFactory.getRepository(tableType),object);
                case NEO4J:
                    return entityAction(action,neo4jRepositoryFactory.getRepository(tableType),object);
                default:
                    return null;
            }

        }

    public BaseRepositories entityToBaseRepo(DBTypes dbTypes, TableTypes tableType){
        switch (dbTypes) {
            case MYSQL:
                return mysqlRepositoryFactory.getRepository(tableType);
            case NEO4J:
                return neo4jRepositoryFactory.getRepository(tableType);
            default:
                return null;
        }

    }

    public PreparedStatement entityAction(DBMethods action, BaseRepositories baseRepository, Object object) throws SQLException {
            switch (action) {
                case INSERT:
                    return baseRepository.insert(object);
                case DELETE:
                    return baseRepository.delete(object);
                case GET:
                    return baseRepository.get(object);
                case UPDATE:
                    return baseRepository.update(object);
                case GET_ALL:
                    return baseRepository.getAll(object);
            }
            return null;
    }
}
