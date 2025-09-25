package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.PaymentDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public List<Payment> getAll() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<Payment> query=session.createQuery("FROM payment ",Payment.class);
            List<Payment> payments=query.list();
            return payments;
        }finally {
//            session.close();
        }
    }

    @Override
    public boolean save(Payment entity) throws SQLException {
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
    public boolean update(Payment entity) throws SQLException, IOException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastID() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Payment> query = session.createQuery("FROM payment ORDER BY id DESC").setMaxResults(1);
            Payment payment = query.getSingleResult();
            return payment.getPaymentId();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Payment entity) throws SQLException, IOException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean ifExit(Payment entity) throws SQLException {
        return false;
    }
}
