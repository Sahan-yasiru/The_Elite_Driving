package org.example.orm_final.dao.custom.impl;

import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.custom.LessonDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.Lesson;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.model.DtoStudent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImpl implements LessonDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNumOF() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query query=session.createQuery(" SELECT count(*) FROM Lesson c");
            return query.uniqueResult()==null?"0":query.uniqueResult()+"";
        }finally {
            session.close();
        }
    }

    @Override
    public Lesson findByID(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Lesson lesson = session.get(Lesson.class, id);
            transaction.commit();
            return lesson;
        } finally {
            session.close();
        }
    }


    @Override
    public List<Lesson> getAll() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<Lesson> query=session.createQuery("FROM Lesson ",Lesson.class);
            List<Lesson> lessons=query.list();
            return lessons;
        }finally {
//            session.close();
        }
    }

    @Override
    public boolean save(Lesson entity) throws SQLException {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Lesson entity) throws SQLException, IOException {
        Session session=factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Lesson entity) throws SQLException, IOException {
        Session session=factoryConfiguration.getSession();
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
    public String getLastID() throws SQLException {
        Session session=factoryConfiguration.getSession();
        try {
            Query<Lesson> query=session.createQuery("FROM Lesson ORDER BY id DESC").setMaxResults(1);
            Lesson lesson=query.getSingleResult();
            return lesson.getLessonId();
        }finally {
            session.close();
        }
    }

    @Override
    public boolean ifExit(Lesson entity) throws SQLException {
        return false;
    }


    public static void main(String[] args) {
        DtoLesson dtoLesson = new DtoLesson();
        dtoLesson.setLessonId("L004");
        dtoLesson.setLessonName("pccc");
        dtoLesson.setTime("pcc");
        dtoLesson.setDate(LocalDate.now());

        DtoInstructor instructor = new DtoInstructor();
        instructor.setId("I001");
        dtoLesson.setInstructor(instructor);

        DtoCourse dtoCourse= new DtoCourse();
        dtoCourse.setId("C001");
        dtoLesson.setCourse(dtoCourse);

        DtoStudent dtoStudent = new DtoStudent();
        dtoStudent.setId("S001");
        dtoLesson.setStudent(dtoStudent);
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        try {
            session.persist(EtyDToConverter.getLessonEty(dtoLesson));
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
