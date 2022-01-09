package database.repositories;


import database.Connectors.interfaces.DatabaseCallBack;
import database.mappers.CrudOperations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.PreparedStatement;

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
}
