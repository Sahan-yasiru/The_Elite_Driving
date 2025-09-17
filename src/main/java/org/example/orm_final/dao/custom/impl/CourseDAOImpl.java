package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.CourseDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public List<Course> getAll() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<Course> courseQuery=session.createQuery("FROM course ", Course.class);
            List<Course> courses=courseQuery.list();
            return courses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Course entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Course entity) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(Course entity) throws SQLException, IOException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException {
        return "";
    }

    @Override
    public boolean ifExit(Course entity) throws SQLException {
        return false;
    }
}
