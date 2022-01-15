package database.Connectors.interfaces;

import database.Connectors.enums.TableTypes;

public interface GenericDB<Param> {
    Param get(Object object,TableTypes tableTypes);
    Param getById(Integer id, TableTypes tableTypes);
    void getAll(TableTypes tableTypes);
    void getAll(TableTypes tableTypes, Object object);
    Integer insertAndGetId(Object object, TableTypes tableTypes);
    void delete(Object object);
    Object deleteById(Integer id,TableTypes tableTypes);
    void update(Object object,TableTypes tableTypes);
}
