package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.UserDAO;
import org.example.orm_final.dao.custom.impl.UserDAOImpl;
import org.example.orm_final.entity.User;
import org.example.orm_final.entity.UserType;
import org.example.orm_final.model.DtoUser;
import org.example.orm_final.model.DtoUserType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserBOImpl  implements UserBO {

    UserDAO userDAO = (UserDAOImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public List<DtoUser> getAll() throws SQLException, IOException {
        return List.of();
    }

    @Override
    public boolean save(DtoUser dtoUser) throws SQLException, IOException {
        return userDAO.save(new User(dtoUser.getId(), dtoUser.getUserType().equals(DtoUserType.Admin)?UserType.Admin:UserType.Receptionist,dtoUser.getUserName(),dtoUser.getPassWold()));
    }

    @Override
    public boolean update(DtoUser dtoUser) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(DtoUser dtoUser) throws SQLException, IOException {
        return false;
    }

    @Override
    public String getID() throws SQLException, IOException {
        return "";
    }
}
