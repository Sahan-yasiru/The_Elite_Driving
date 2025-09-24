package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.StudentBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.CourseDAO;
import org.example.orm_final.dao.custom.QueryDAO;
import org.example.orm_final.dao.custom.StudentDAO;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.entity.Course;
import org.example.orm_final.entity.Student;
import org.example.orm_final.model.DtoStudent;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO= (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    private QueryDAO queryDAO= (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Query);
    private CourseDAO  courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Course);


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
        Student student=EtyDToConverter.getStudentEty(dtoStudent);

        List<Course> courses=new ArrayList<>();
        for (String coursids : dtoStudent.getCourses()) {
            courses.add(courseDAO.getCourseById(coursids));
        }
        student.setCourses(courses);
        return studentDAO.save(student);
    }

    @Override
    public boolean update(DtoStudent dtoStudent) throws SQLException, IOException {
        Student student=EtyDToConverter.getStudentEty(dtoStudent);

        List<Course> courses=new ArrayList<>();
        for (String coursids : dtoStudent.getCourses()) {
            courses.add(courseDAO.getCourseById(coursids));
        }
        student.setCourses(courses);
        return studentDAO.update(student);
    }

    @Override
    public boolean delete(DtoStudent dtoStudent) throws SQLException, IOException {
        Student student=studentDAO.findById(dtoStudent.getId());
        return studentDAO.delete(student);


    }

    @Override
    public String getLastID() throws SQLException {
        try {
            String id=studentDAO.getLastID();
            int intId=Integer.parseInt(id.replaceAll("\\D+", "")) + 1;
            return String.format("S"+"%03d", intId);
        }catch (Exception e){
            e.printStackTrace();
            return "S001";
        }
    }

    @Override
    public boolean ifExit(DtoStudent dtoStudent) throws SQLException {
        return false;
    }

    @Override
    public String getNumOF() throws SQLException {
        return studentDAO.getNumOF();
    }
}
