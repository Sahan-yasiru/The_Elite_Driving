package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.InstructorDAO;
import org.example.orm_final.dao.custom.impl.InstructorDAOImpl;
import org.example.orm_final.model.DtoInstructor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    private InstructorDAO instructorDAO= (InstructorDAOImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Instructor);


    @Override
    public List<DtoInstructor> getAll() throws SQLException {
        List<DtoInstructor> dtoInstructors=new ArrayList<>();
        instructorDAO.getAll().forEach(instructor -> {
            dtoInstructors.add(EtyDToConverter.getInstructorDto(instructor));
        });
        return dtoInstructors;
    }

    @Override
    public boolean save(DtoInstructor dto) throws SQLException {
        return instructorDAO.save(EtyDToConverter.getInstructorEty(dto));
    }

    @Override
    public boolean update(DtoInstructor entity) throws SQLException, IOException {
        return instructorDAO.update(EtyDToConverter.getInstructorEty(entity));
    }

    @Override
    public boolean delete(DtoInstructor dto) throws SQLException, IOException {
        return instructorDAO.delete(EtyDToConverter.getInstructorEty(dto));
    }

    @Override
    public String getLastID() throws SQLException {
        try {
            String id=instructorDAO.getLastID();
            int intId=Integer.parseInt(id.replaceAll("\\D+", "")) + 1;
            return String.format("I"+"%03d", intId);
        }catch (Exception e){
            e.printStackTrace();
            return "I001";
        }
    }

    @Override
    public boolean ifExit(DtoInstructor entity) throws SQLException {
        return false;
    }

}
