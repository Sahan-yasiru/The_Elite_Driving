package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.QueryDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QuryDAOImpl implements QueryDAO {
    FactoryConfiguration  factoryConfiguration=FactoryConfiguration.getInstance();
    @Override
    public List<Object[]> getStudentsWithCous() {
        FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
        Session session=factoryConfiguration.getSession();
        try {
            Query<Object[]> studentQuery=session.createQuery("SELECT s.id, c.id FROM student s JOIN s.courses c",Object[].class);
            List<Object[]> result = studentQuery.getResultList();
           return result;
        } finally {
            session.close();
        }

    }

}
