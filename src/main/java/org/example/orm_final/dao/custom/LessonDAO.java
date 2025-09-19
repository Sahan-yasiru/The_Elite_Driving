package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.CrudDAO;
import org.example.orm_final.entity.Lesson;

import java.sql.SQLException;

public interface LessonDAO extends CrudDAO<Lesson> {
    Lesson findByID(String id) throws SQLException;
}
