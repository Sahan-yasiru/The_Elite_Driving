package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.entity.user.DtoUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    List<DtoUser> getAll() throws SQLException, IOException;

    boolean save(DtoUser dtoUser) throws SQLException;

    boolean update(DtoUser dtoUser) throws SQLException, IOException;

    boolean delete(DtoUser dtoUser) throws SQLException, IOException;

    String getID() throws SQLException;
    
    boolean ifExit(DtoUser dtoUser) throws SQLException,IOException;

}
