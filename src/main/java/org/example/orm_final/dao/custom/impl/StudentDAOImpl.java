package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.QueryDAO;
import org.example.orm_final.dao.custom.StudentDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Lesson;
import org.example.orm_final.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    private FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNumOF() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query query=session.createQuery(" SELECT count(*) FROM student c");
            return query.uniqueResult()==null?"0":query.uniqueResult()+"";
        }finally {
            session.close();
        }
    }

    @Override
    public Student findById(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            return session.get(Student.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();

        try {
            Query<Student> query = session.createQuery("from student ", Student.class);
            List<Student> list = query.list();
            return list;
        } finally {
//            session.close();
        }

    }

    @Override
    public boolean save(Student entity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Student entity) throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Student entity) throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastID() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Student> query = session.createQuery("FROM student ORDER BY id DESC").setMaxResults(1);
            Student student = query.getSingleResult();
            return student.getId();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean ifExit(Student entity) throws SQLException {
        return false;
    }
}
