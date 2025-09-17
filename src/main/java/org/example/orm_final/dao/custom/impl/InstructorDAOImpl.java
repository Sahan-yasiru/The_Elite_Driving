package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.custom.InstructorDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Course;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    private FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Instructor> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
//            Query<Instructor> instructorQuery = session.createQuery(
//                    "SELECT DISTINCT i FROM instructor i LEFT JOIN FETCH i.courses",
//                    Instructor.class
//            );
//            Query<Course> courseQuery=session.createQuery("FROM course ",Course.class);
            Query<Instructor> instructorQuery = session.createQuery("FROM instructor ", Instructor.class);
            List<Instructor> instructors = instructorQuery.list();
            return instructors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Instructor entity) throws SQLException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Instructor entity) throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();;
        }
    }

    @Override
    public boolean delete(Instructor entity) throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Instructor instructor =  session.get(Instructor.class, entity.getId());
            if(instructor!=null){
                session.delete(instructor);
                transaction.commit();

            }
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastID() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> lastid = session.createQuery("SELECT id FROM instructor ORDER BY id DESC", String.class).setMaxResults(1);
            return lastid.getSingleResult();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean ifExit(Instructor entity) throws SQLException {
        return false;
    }

}