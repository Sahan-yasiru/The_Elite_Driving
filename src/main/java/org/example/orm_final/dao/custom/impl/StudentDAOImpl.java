package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.StudentDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public List<Student> getAll() throws SQLException {
        Session session=factoryConfiguration.getSession();

            Query<Student> query=session.createQuery("from student ",Student.class);
            List<Student> list=query.list();
            return list;

    }

    @Override
    public boolean save(Student entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student entity) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(Student entity) throws SQLException, IOException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException {
        return "";
    }

    @Override
    public boolean ifExit(Student entity) throws SQLException {
        return false;
    }
}
