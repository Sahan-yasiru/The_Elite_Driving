package org.example.orm_final.dao;

import org.example.orm_final.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null?new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        User,
        Instructor,
        Course,
        Lesson,
        Student
    }
    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){
            case User -> {
                return new UserDAOImpl();
            }
            case Instructor -> {
                return new InstructorDAOImpl();
            }
            case Course -> {
                return new CourseDAOImpl();
            }
            case Lesson -> {
                return new LessonDAOImpl();
            }
            case Student -> {
                return new StudentDAOImpl();
            }

            default -> {
                return null;
            }
        }
    }
}
