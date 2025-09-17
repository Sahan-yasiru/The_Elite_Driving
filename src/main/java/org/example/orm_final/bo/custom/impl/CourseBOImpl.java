package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.CourseDAO;
import org.example.orm_final.dao.custom.impl.CourseDAOImpl;
import org.example.orm_final.model.DtoCourse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO=(CourseDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Course);
    @Override
    public List<DtoCourse> getAll() throws SQLException{
        List<DtoCourse> dtoCourses=new ArrayList<>();
        courseDAO.getAll().forEach(course -> {
            dtoCourses.add(EtyDToConverter.getCourseDTO(course));
        });
        return dtoCourses;
    }

    @Override
    public boolean save(DtoCourse dtoCourse) throws SQLException {
        return false;
    }

    @Override
    public boolean update(DtoCourse dtoCourse) throws SQLException, IOException {
        return false;
    }

    @Override
    public boolean delete(DtoCourse dtoCourse) throws SQLException, IOException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException {
        return "";
    }

    @Override
    public boolean ifExit(DtoCourse dtoCourse) throws SQLException {
        return false;
    }
}
