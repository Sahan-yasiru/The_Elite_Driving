package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.model.DtoCourse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    public List<DtoCourse> getAll() throws SQLException;
    public boolean save(DtoCourse dtoCourse)throws SQLException;
    public boolean update(DtoCourse dtoCourse)throws SQLException, IOException;
    public boolean delete(DtoCourse dtoCourse)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(DtoCourse dtoCourse) throws SQLException;

}
