package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.CourseDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();

    @Override
    public String getNumOF() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query query=session.createQuery(" SELECT count(*) FROM course c");
            return query.uniqueResult()==null?"0":query.uniqueResult()+"";
        }finally {
            session.close();
        }
    }

    @Override
    public Course getCourseById(String id) throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Course course=session.get(Course.class,id);
            return course;
        }finally {
            session.close();
        }
    }

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
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Course entity) throws SQLException, IOException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Course entity) throws SQLException, IOException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastID() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<Course>  courseQuery=session.createQuery("FROM course ORDER BY id DESC").setMaxResults(1);
            Course course=courseQuery.getSingleResult();
            return course.getId();
        }finally {
            session.close();
        }
    }

    @Override
    public boolean ifExit(Course entity) throws SQLException {
        return false;
    }
}
