package org.example.orm_final.bo.custom.impl;

import jakarta.persistence.PersistenceException;
import lombok.Getter;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.bo.exception.DuplicateID;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.UserDAO;
import org.example.orm_final.dao.util.BCryptHashing;
import org.example.orm_final.entity.user.User;
import org.example.orm_final.entity.user.UserType;
import org.example.orm_final.model.user.DtoUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserBOImpl implements UserBO {

    private UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.User);
    @Getter
    private static String userType;


    @Override
    public boolean ifExit(DtoUser dtoUser) {
        try {
            if(userDAO.chackUserEmp()) {
                System.out.println(userDAO.chackUserEmp());
                if (dtoUser.getUserName().equals("ADMIN") && dtoUser.getPassWold().equals("ADMIN")) {
                    userType = "ADMIN";
                    return true;
                }
            }
            User user = new User();
            user.setUserName(dtoUser.getUserName());
            user.setPassWold(dtoUser.getPassWold());

            User user1 = userDAO.ifExitSP(user);
            if (user1 != null) {
                userType = user1.getUserType().equals(UserType.Admin)?"Admin":"Receptionist";
                System.out.println(userType);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DtoUser> getAll() throws SQLException, IOException {
        List<DtoUser> users = new ArrayList<>();
        userDAO.getAll().forEach(user -> {
            user.setPassWold("Password is encrypted");
            users.add(EtyDToConverter.getUserDto(user));
        });
        return users;
    }

    @Override
    public boolean save(DtoUser dtoUser) {
        dtoUser.setPassWold(BCryptHashing.getHashedPassword(dtoUser.getPassWold()));
        try {
            boolean b = userDAO.ifExit(EtyDToConverter.getUserEty(dtoUser));
            if (b) {
                throw new DuplicateID("user already exits");
            } else {
                return userDAO.save(EtyDToConverter.getUserEty(dtoUser));
            }
        } catch (PersistenceException | SQLException e) {
            throw new DuplicateID("user already exits");
        }
    }

    @Override
    public boolean update(DtoUser dtoUser) throws SQLException, IOException {
        try {
//            boolean b = userDAO.ifExit(EtyDToConverter.getUserEty(dtoUser));
//            if (b){
//                throw new DuplicateID("user already exits");
//            }else{
            dtoUser.setPassWold(BCryptHashing.getHashedPassword(dtoUser.getPassWold()));
            return userDAO.update(EtyDToConverter.getUserEty(dtoUser));
//            }
        } catch (PersistenceException | SQLException e) {
            throw new DuplicateID("user already exits");
        }
    }

    @Override
    public boolean delete(DtoUser dtoUser) throws SQLException, IOException {
        return userDAO.delete(EtyDToConverter.getUserEty(dtoUser));
    }

    @Override
    public String getID() throws SQLException {
        try {
            int id = Integer.parseInt(userDAO.getLastID()) + 1;
            return id + "";
        } catch (Exception e) {
            e.printStackTrace();
            return 1 + "";
        }
    }

}
