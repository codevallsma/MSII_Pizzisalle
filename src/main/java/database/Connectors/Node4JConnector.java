package database.Connectors;

import Utils.Passwords;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;

import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * WE ARE USING THE SINGLETON PATTERN
 * The mysql connector can only be created once
 */
public class Node4JConnector extends GeneralDBConnector {

    private String url;
    static Connection conn = null;
    static Statement s;

    private Node4JConnector() {
        super(DBTypes.NEO4J);
        url  = "jdbc:neo4j:bolt://localhost";
    }

    private static final class InstanceHolder {
        private static final Node4JConnector instance = new Node4JConnector();
    }

    public static Node4JConnector getInstance() {
        return InstanceHolder.instance;
    }
    @Override
    protected String getPassword() {
        return Passwords.getInstance().getEnvVariablesByType("NEO4J_PASSWORD");
    }

    @Override
    protected String getUserName() {
        return Passwords.getInstance().getEnvVariablesByType("NEO4J_USER");
    }

    @Override
    protected String getDb() {
        return Passwords.getInstance().getEnvVariablesByType("NEO4J_DB_NAME");
    }

    @Override
    protected String getPort() {
        return Passwords.getInstance().getEnvVariablesByType("NEO4J_PORT");
    }

    @Override
    public void connect() throws SQLException, IllegalArgumentException {
    }

    @Override
    public void startObservingTable(TableTypes tableTypes, PropertyChangeListener pcl) {

    }

    @Override
    public void stopObservingTable(TableTypes tableTypes, PropertyChangeListener pcl) {

    }

    @Override
    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconnectat!");
        } catch (Exception e) {
            System.err.println("Trouble finishing the connection with the neo4j database --> ");
        }
    }

    public  Connection getConn() {
        return conn;
    }
    @Override
    public Object get(Object object, TableTypes tableTypes) {
        return null;
    }

    @Override
    public Object getById(Integer id, TableTypes tableTypes) {
        return null;
    }

    @Override
    public void getAll(TableTypes tableTypes) { }

    @Override
    public void getAll(TableTypes tableTypes, Object object) {

    }

    @Override
    public Integer insertAndGetId(Object object, TableTypes tableTypes) {
        return null;
    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public Object deleteById(Integer id, TableTypes tableTypes) {
        return null;
    }

    @Override
    public void update(Object object, TableTypes tableTypes) {

    }

}