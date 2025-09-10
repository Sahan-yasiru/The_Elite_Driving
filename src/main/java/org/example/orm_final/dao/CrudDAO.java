package org.example.orm_final.dao;

import org.example.orm_final.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public List<T> getAll() throws SQLException, IOException;
    public boolean save(T entity)throws SQLException, IOException;
    public boolean update(T entity)throws SQLException, IOException;
    public boolean delete(T entity)throws SQLException, IOException;
    public String getID()throws SQLException, IOException;


}
