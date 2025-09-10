package org.example.orm_final.dao;

import org.example.orm_final.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null?new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        User
    }
    public SuperDAO getDAO(DAOTypes daoType){
        switch (daoType){
            case User -> {
                return new UserDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
