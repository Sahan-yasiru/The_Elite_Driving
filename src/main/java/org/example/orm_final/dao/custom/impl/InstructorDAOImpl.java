package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.dao.custom.InstructorDAO;
import org.example.orm_final.dao.custom.LessonDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.Lesson;
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
    public String getNumOF() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query query=session.createQuery(" SELECT count(*) FROM instructor c");
            return query.uniqueResult()==null?"0":query.uniqueResult()+"";
        }finally {
            session.close();
        }
    }

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

//    public static void main(String[] args) throws SQLException {
//        LessonDAO lessonDAO = new LessonDAOImpl();
//        Instructor instructor = new Instructor("I0010", "Nimal", "nimal@gmail.com");
//        Lesson lesson = lessonDAO.findByID("L001");
//
//        List<Lesson> lessonList=new ArrayList<Lesson>();
//        lessonList.add(lesson);
//        instructor.setLessons(lessonList);
//        InstructorDAOImpl instructorDAO = new InstructorDAOImpl();
//
//        instructorDAO.save(instructor);
//
//    }
//    public static void main(String[] args,String id) throws SQLException {
//        LessonDAO lessonDAO = new LessonDAOImpl();
////        Instructor instructor = new Instructor("I0010", "Nimal", "nimal@gmail.com");
//        Lesson lesson = lessonDAO.findByID("L001");
//
//        // set both sides
//        lesson.setInstructor(instructor);   // important
//        List<Lesson> lessonList = new ArrayList<>();
//        lessonList.add(lesson);
//        instructor.setLessons(lessonList);
//
//        InstructorDAOImpl instructorDAO = new InstructorDAOImpl();
//        instructorDAO.save(instructor);
//    }


}