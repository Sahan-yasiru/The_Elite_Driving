package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.UserDAO;
import org.example.orm_final.dao.util.BCryptHashing;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.user.User;
import org.example.orm_final.entity.user.UserType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    private FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();


    @Override
    public boolean ifExit(User user) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query query = session.createQuery("SELECT COUNT(u) FROM user u  WHERE userName =: userName", User.class);
        query.setParameter("userName", user.getUserName());
        try {
            Long l = (Long) query.uniqueResult();
            if (l>0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public boolean ifExitSP(User user) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Query<User> query = session.createQuery("FROM user  WHERE userName =: userName", User.class);
        query.setParameter("userName", user.getUserName());

        List<User> user1 = query.list();
        System.out.println(user1.getFirst().getUserType());

        boolean b=BCryptHashing.chackHashedPassword(user.getPassWold(),user1.getFirst().getPassWold());
        session.close();
        return b;

    }

    @Override
    public List<User> getAll() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<User> userQuery=session.createQuery("FROM user", User.class);
            List<User> userList=userQuery.list();
            return userList;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean save(User entity) throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            System.out.println("works");
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(User entity) throws SQLException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction!=null) transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(User entity) throws SQLException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastID() throws SQLException {

       Session session=factoryConfiguration.getSession();
       try {
           Query<Integer> query = session.createQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1", Integer.class);
           int id = query.getSingleResult();
           session.close();
           return id + "";
       }finally {
           session.close();
       }
    }


}
