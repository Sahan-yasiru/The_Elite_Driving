package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.model.DtoStudent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<DtoStudent> getAll() throws SQLException;
    public boolean save(DtoStudent dtoStudent)throws SQLException;
    public boolean update(DtoStudent dtoStudent)throws SQLException, IOException;
    public boolean delete(DtoStudent dtoStudent)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(DtoStudent dtoStudent) throws SQLException;
    String getNumOF() throws SQLException;

}
