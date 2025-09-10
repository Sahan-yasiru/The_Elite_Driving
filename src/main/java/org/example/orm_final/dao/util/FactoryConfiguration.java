package org.example.orm_final.dao.util;

import org.example.orm_final.entity.Student;
import org.example.orm_final.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private SessionFactory sessionFactory;
    private static FactoryConfiguration factoryConfiguration;

    private FactoryConfiguration() throws IOException {
        Properties  properties=new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));


        Configuration configuration=new Configuration();
        configuration.setProperties(properties);

//        configuration.configure();

        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(User.class);


        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() throws IOException {
        return factoryConfiguration==null?new FactoryConfiguration(): factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
