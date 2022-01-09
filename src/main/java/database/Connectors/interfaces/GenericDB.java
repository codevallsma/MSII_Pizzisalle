package database.Connectors.interfaces;

import database.Connectors.enums.TableTypes;
import database.Connectors.interfaces.DatabaseCallBack;

import java.util.List;

public interface GenericDB<Param> {
    Param get(Object object,TableTypes tableTypes);
    Param getById(Integer id, TableTypes tableTypes);
    List<Param> getAll(TableTypes tableTypes);
    Integer insertAndGetId(Object object, TableTypes tableTypes);
    void delete(Object object);
    Object deleteById(Integer id,TableTypes tableTypes);
    void update(Object object,TableTypes tableTypes);
}
