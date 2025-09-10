package org.example.orm_final.bo;

import org.example.orm_final.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?new BOFactory():boFactory;
    }

    public enum  BOTypes{
        User
    }
    public SuperBO getBO(BOTypes boType){
        switch (boType){
            case User -> {
                return new UserBOImpl();
            }
            default -> {
                return null;
            }
        }

    }
}
