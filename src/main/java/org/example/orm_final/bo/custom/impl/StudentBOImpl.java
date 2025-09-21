package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.StudentBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.StudentDAO;
import org.example.orm_final.model.DtoStudent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO= (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    @Override
    public List<DtoStudent> getAll() throws SQLException {
        List<DtoStudent> dtoStudents=new ArrayList<>();
        studentDAO.getAll().forEach(student->{
            dtoStudents.add(EtyDToConverter.getStudentDto(student));
        });
        return dtoStudents;
    }

    @Override
    public boolean save(DtoStudent dtoStudent) throws SQLException {
        return false;
    }

    @Override
    public boolean update(DtoStudent dtoStudent) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(DtoStudent dtoStudent) throws SQLException, IOException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException {
        return "";
    }

    @Override
    public boolean ifExit(DtoStudent dtoStudent) throws SQLException {
        return false;
    }
}
