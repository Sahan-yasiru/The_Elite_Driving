package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.CrudDAO;
import org.example.orm_final.entity.Course;

import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course> {
    public Course getCourseById(String id) throws SQLException;

    String getNumOF() throws SQLException;
}
