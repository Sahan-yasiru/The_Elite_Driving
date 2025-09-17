package org.example.orm_final.bo;

import org.example.orm_final.bo.custom.impl.CourseBOImpl;
import org.example.orm_final.bo.custom.impl.InstructorBOImpl;
import org.example.orm_final.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?new BOFactory():boFactory;
    }

    public enum  BOTypes{
        User,
        Instructor,
        Course
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
            default -> {
                return null;
            }
        }

    }
}
