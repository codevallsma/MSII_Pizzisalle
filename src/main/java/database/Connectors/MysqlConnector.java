package database.Connectors;

import Utils.Passwords;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import database.Connectors.interfaces.GenericDB;
import database.mappers.MapMethodToFunction;
import database.repositories.BaseRepositories;
import model.ModifyRamClasses;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * WE ARE USING THE SINGLETON PATTERN
 * The mysql connector can only be created once
 */
public class MysqlConnector extends GeneralDBConnector implements GenericDB {

    private String url;
    static Connection conn = null;
    static Statement s;
    private boolean subquery;

    private MysqlConnector() {
        super(DBTypes.MYSQL);
        url  = "jdbc:mysql://localhost";
        url += ":"+ this.port +"/";
        url += Passwords.getInstance().getEnvVariablesByType("MYSQL_DB_NAME");
        url += "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        subquery = false;
    }

    public boolean isSubquery() {
        return subquery;
    }

    public void setSubquery(boolean subquery) {
        this.subquery = subquery;
    }

    // INSERT -> We only need the id of the recently inserted element
    private Integer executeInsert(PreparedStatement ps, BaseRepositories  baseRepository, ModifyRamClasses object) throws SQLException {
        //we execute the insert query
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        Integer id = rs.getInt(1);
        object.insertID(id);
        if(!subquery)baseRepository.onSuccess(object,DBMethods.INSERT);
        return id;
    }

    // UPDATE - > We need the inserted object in order to update it in the observer classes
    // DELETE -> We need to delete the whole object from the subscriber classes
    private void executeUpdateOrDelete(PreparedStatement ps, BaseRepositories  baseRepository, Object object, DBMethods dbMethods) throws SQLException {
        //we execute the update query
        ps.executeUpdate();
        baseRepository.onSuccess(object, dbMethods);
    }

    // SELECT -> We do need the searched element in the subscriber classes
    private void executeSelect(PreparedStatement ps, BaseRepositories  baseRepository) throws SQLException {
        //we execute the  query
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        if(!subquery)baseRepository.onSuccess(rs,DBMethods.GET);
    }

    private void executeSelectAll(PreparedStatement ps, BaseRepositories  baseRepository) throws SQLException {
        //we execute the select query
        ResultSet rs = ps.executeQuery();
        //multiple results, so we will create a list of results
        List<Object> list  = new ArrayList<Object>();
        //loop while there is more results to retrieve
        while (rs.next()){
            //add the current result to the list of results
            list.add(baseRepository.resultSetToObject(rs));
        }
        if(!subquery)baseRepository.onSuccess(list,DBMethods.GET_ALL);
    }

    @Override
    public Object get(Object object, TableTypes tableTypes) {
        return null;
    }

    @Override
    public void getById(Integer id, TableTypes tableTypes) {
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.GET_BY_ID, br, id);
            this.executeSelect(ps, br);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getAll(TableTypes tableTypes) {
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.GET_ALL, br, null);
            this.executeSelectAll(ps, br);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAll(TableTypes tableTypes, Object object) {
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.GET_ALL, br, object);
            this.executeSelectAll(ps, br);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer insertAndGetId(Object object, TableTypes tableTypes) {
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.INSERT, br, object);
            return executeInsert(ps, br, (ModifyRamClasses) object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.UPDATE, br, object);
            executeUpdateOrDelete(ps, br,object, DBMethods.UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static final class InstanceHolder {
        private static final MysqlConnector instance = new MysqlConnector();
    }

    public static MysqlConnector getInstance() {
        return InstanceHolder.instance;
    }
    @Override
    protected String getPassword() {
        return Passwords.getInstance().getEnvVariablesByType("MYSQL_PASSWORD");
    }

    @Override
    protected String getUserName() {
        return Passwords.getInstance().getEnvVariablesByType("MYSQL_USER");
    }

    @Override
    protected String getDb() {
        return Passwords.getInstance().getEnvVariablesByType("MYSQL_DB_NAME");
    }

    @Override
    protected String getPort() {
        return Passwords.getInstance().getEnvVariablesByType("MYSQL_PORT");
    }

    @Override
    public void connect() throws SQLException, ClassNotFoundException, IllegalArgumentException {
        if (this.userName == null || this.password == null || db == null) {
            throw new IllegalArgumentException("No username or password or database name given!");
        }
        conn =  DriverManager.getConnection(url, userName, password);
        if (conn != null) {
            System.out.println("Conexió a base de dades "+url+" ... Ok");
        }
    }

    @Override
    public void startObservingTable(TableTypes tableTypes, PropertyChangeListener pcl) {
        BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
        br.addObserver(pcl);
    }

    @Override
    public void stopObservingTable(TableTypes tableTypes, PropertyChangeListener pcl) {
        BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
        br.removeObserver(pcl);
    }


    @Override
    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconnectat!");
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

    public  Connection getConn() {
        return conn;
    }
}