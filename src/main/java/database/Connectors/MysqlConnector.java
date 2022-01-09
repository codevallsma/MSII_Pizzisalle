package database.Connectors;

import Utils.Passwords;
import database.Connectors.enums.DBMethods;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import database.Connectors.interfaces.DatabaseCallBack;
import database.Connectors.interfaces.GenericDB;
import database.mappers.MapMethodToFunction;
import database.repositories.BaseRepositories;
import model.ModifyRamClasses;

import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.sql.ResultSet.*;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

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
        System.out.println(id);
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
    private Object executeSelect(PreparedStatement ps, BaseRepositories  baseRepository) throws SQLException {
        //we execute the  query
        ResultSet rs = ps.executeQuery();
        if(!subquery)baseRepository.onSuccess(rs,DBMethods.GET);
        return ps.getGeneratedKeys().getInt(1);
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
    public List getAll(TableTypes tableTypes) {
        return null;
    }

    @Override
    public Integer insertAndGetId(Object object, TableTypes tableTypes) {
        try {
            BaseRepositories br = MapMethodToFunction.getInstance().entityToBaseRepo(this.dbType, tableTypes);
            PreparedStatement ps = MapMethodToFunction.getInstance().entityAction(DBMethods.INSERT, br, object);
            Integer id = executeInsert(ps, br, (ModifyRamClasses) object);
            System.out.println("El id es " + id);
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