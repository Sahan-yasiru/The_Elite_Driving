package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.UserDAO;
import org.example.orm_final.dao.util.BCryptHashing;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() throws SQLException, IOException {
        return List.of();
    }

    @Override
    public boolean save(User entity) throws SQLException,IOException{
        entity.setPassWold(BCryptHashing.getHashedPassword(entity.getPassWold()));
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(User entity) throws SQLException {
        return false;
    }

    @Override
    public String getID() throws SQLException {
        return "";
    }
}
