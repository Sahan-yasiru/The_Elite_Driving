package org.example.orm_final.dao.custom;

import org.example.orm_final.dao.CrudDAO;
import org.example.orm_final.entity.user.User;

public interface UserDAO extends CrudDAO<User> {

    boolean ifExitSP(User user) throws Exception;

}
