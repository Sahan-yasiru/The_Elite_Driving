package org.example.orm_final.dao.util;

import org.example.orm_final.entity.Course;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private SessionFactory sessionFactory;
    private static FactoryConfiguration factoryConfiguration;

    private FactoryConfiguration()  {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Configuration configuration=new Configuration();
        configuration.setProperties(properties);

//        configuration.configure();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Course.class);


        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance()  {
        return factoryConfiguration==null?new FactoryConfiguration(): factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
