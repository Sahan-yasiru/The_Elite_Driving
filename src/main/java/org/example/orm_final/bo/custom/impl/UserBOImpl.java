package org.example.orm_final.bo.custom.impl;

import jakarta.persistence.PersistenceException;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.bo.exception.DuplicateID;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.UserDAO;
import org.example.orm_final.dao.util.BCryptHashing;
import org.example.orm_final.entity.user.User;
import org.example.orm_final.model.user.DtoUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.User);


    @Override
    public boolean ifExit(DtoUser dtoUser) {
        try {
            User user=new User();
            user.setUserName(dtoUser.getUserName());
            user.setPassWold(dtoUser.getPassWold());
            return userDAO.ifExit(user);
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
            if (b){
                throw new DuplicateID("user already exits");
            }else{
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
