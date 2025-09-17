package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.model.DtoInstructor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InstructorBO extends SuperBO {
    public List<DtoInstructor> getAll() throws SQLException;
    public boolean save(DtoInstructor entity)throws SQLException;
    public boolean update(DtoInstructor entity)throws SQLException, IOException;
    public boolean delete(DtoInstructor entity)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(DtoInstructor entity) throws SQLException;
}
