package database.Connectors;

import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import database.Connectors.interfaces.GenericDB;

import java.beans.PropertyChangeListener;

//we have to create a general database that manages all the other databases:
//  - mysql
// - neo4j

public abstract class GeneralDBConnector implements GenericDB {
    protected String userName;
    protected String password;
    protected String db;
    protected String port;
    protected DBTypes dbType;

    protected GeneralDBConnector(DBTypes dbType) {
        this.userName = getUserName();
        this.password = getPassword();
        this.db = getDb();
        this.port = getPort();
        this.dbType = dbType;
    }

    public static GeneralDBConnector getDB(DBTypes dbType){
        switch (dbType){
            case MYSQL:
                return MysqlConnector.getInstance();
            case NEO4J:
                return Node4JConnector.getInstance();
        }
        return null;
    }
    protected abstract String getPassword();
    protected abstract String getUserName();
    protected abstract String getDb();
    protected abstract String getPort();
    public abstract void connect() throws Exception;
    public abstract void startObservingTable(TableTypes tableTypes, PropertyChangeListener pcl);
    public abstract void stopObservingTable(TableTypes tableTypes, PropertyChangeListener pcl);
    public abstract void disconnect();
}
