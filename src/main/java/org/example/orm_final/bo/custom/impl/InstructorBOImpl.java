package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.custom.LessonBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.InstructorDAO;
import org.example.orm_final.dao.custom.LessonDAO;
import org.example.orm_final.dao.custom.impl.InstructorDAOImpl;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.Lesson;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.view.instructor.InstructorTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    private InstructorDAO instructorDAO= (InstructorDAOImpl)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Instructor);
    private LessonDAO lessonDAO=(LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Lesson);

    @Override
    public String getNumOF() throws SQLException {
        return instructorDAO.getNumOF();
    }

    @Override
    public List<DtoInstructor> getAll() throws SQLException {
        List<DtoInstructor> dtoInstructors=new ArrayList<>();
        instructorDAO.getAll().forEach(instructor -> {
            dtoInstructors.add(EtyDToConverter.getInstuctorDTO(instructor));
        });
        return dtoInstructors;
    }

    @Override
    public boolean save(DtoInstructor dto) throws SQLException {
        Instructor instructor=EtyDToConverter.getInstructorEty(dto);
        List<Lesson> lessons=new ArrayList<>();
        for (Lesson lesson: instructor.getLessons()) {
            lessons.add(lessonDAO.findByID(lesson.getLessonId()));
        }
        instructor.setLessons(lessons);
       return instructorDAO.save(instructor);
    }

    @Override
    public boolean update(DtoInstructor dto) throws SQLException, IOException {
        Instructor instructor=EtyDToConverter.getInstructorEty(dto);
        return instructorDAO.update(instructor);

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

    public static void main(String[] args) throws SQLException {

    }

}
