package org.example.orm_final.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public List<T> getAll() throws SQLException;
    public boolean save(T entity)throws SQLException;
    public boolean update(T entity)throws SQLException, IOException;
    public boolean delete(T entity)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(T entity) throws SQLException;


}
