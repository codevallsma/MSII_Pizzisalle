package model;

/**
 * Strategy pattern in order to insert the id in the database without caring about the type of class that implements this interface
 */
public interface ModifyRamClasses {
    void insertID(Integer id);
    String getName();
}
