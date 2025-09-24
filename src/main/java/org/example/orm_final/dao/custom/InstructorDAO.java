package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.CrudDAO;
import org.example.orm_final.entity.Instructor;

import java.sql.SQLException;

public interface InstructorDAO extends CrudDAO<Instructor> {
    String getNumOF() throws SQLException;
}
