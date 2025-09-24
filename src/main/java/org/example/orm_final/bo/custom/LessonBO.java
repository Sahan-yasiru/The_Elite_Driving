package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    public List<DtoLesson> getAll() throws SQLException;
    public boolean save(DtoLesson dtoLesson)throws SQLException;
    public boolean update(DtoLesson dtoLesson)throws SQLException, IOException;
    public boolean delete(DtoLesson dtoLesson)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(DtoLesson dtoLesson) throws SQLException;
    String getNumOF() throws SQLException;


}
