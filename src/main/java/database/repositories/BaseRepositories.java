package database.repositories;


import database.Connectors.enums.DBMethods;
import database.Connectors.enums.TableTypes;
import database.Connectors.interfaces.DatabaseCallBack;
import database.mappers.CrudOperations;
import model.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * This class is the one that groups all the repositories of all this project
 */
public abstract class BaseRepositories implements DatabaseCallBack, CrudOperations<PreparedStatement,Object> {
    /**
     * Property change observer that alerts the observing class when an interaction
     * with the database has made a change to some or all fields of a class
      */
    protected PropertyChangeSupport observer;

    public BaseRepositories() {
        observer = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener pcl) {
        this.observer.addPropertyChangeListener(pcl);
    }

    public void removeObserver(PropertyChangeListener pcl) {
        this.observer.removePropertyChangeListener(pcl);
    }
    public void onSuccessBaseRepo(Object object,Object oldValue, DBMethods dbMethods, String table){
        try{
            Object newObject = null;
            switch (dbMethods){
                case INSERT: case UPDATE: case GET_ALL:
                    // we just inserted this object in the database
                    newObject = object;
                    break;
                case GET: case GET_BY_ID:
                    newObject = resultSetToObject((ResultSet) object);
                    break;
                case DELETE:
                    // to delete the whole object we will replace the current object for one that it is null
                    // however it is already assigned
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dbMethods);
            }
            this.observer.firePropertyChange(table, oldValue, newObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
