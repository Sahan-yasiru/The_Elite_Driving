package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.CrudDAO;
import org.example.orm_final.entity.user.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

    User ifExitSP(User user) throws Exception;

    User getType(int id) throws SQLException;

}
