package database.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudOperations <ReturnParameter,Object> {
  ReturnParameter insert(Object object) throws SQLException;
  ReturnParameter delete(Object object);
  ReturnParameter update(Object object);
  ReturnParameter get(Object object);
  Object resultSetToObject(ResultSet resultSet) throws SQLException;
}
