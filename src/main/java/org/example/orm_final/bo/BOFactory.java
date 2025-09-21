package org.example.orm_final.bo;

import org.example.orm_final.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?new BOFactory():boFactory;
    }

    public enum  BOTypes{
        User,
        Instructor,
        Course,
        Lesson,
        Student
    }
    public SuperBO getBO(BOTypes boType){
        switch (boType){
            case User -> {
                return new UserBOImpl();
            }
            case Instructor -> {
                return new InstructorBOImpl();
            }
            case Course -> {
                return new CourseBOImpl();
            }
            case Lesson -> {
                return new LessonBOImpl();
            }
            case Student -> {
                return new StudentBOImpl();
            }
            default -> {
                return null;
            }
        }

    }
}
