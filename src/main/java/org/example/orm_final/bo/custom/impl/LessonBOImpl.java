package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.LessonBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.LessonDAO;
import org.example.orm_final.dao.custom.impl.LessonDAOImpl;
import org.example.orm_final.model.DtoLesson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonBOImpl implements LessonBO {
    LessonDAO lessonDAO= (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Lesson);

    @Override
    public String getNumOF() throws SQLException {
        return lessonDAO.getNumOF();
    }

    @Override
    public List<DtoLesson> getAll() throws SQLException {
        List<DtoLesson> dtoLessons=new ArrayList<>();
        lessonDAO.getAll().forEach(lesson->{
            dtoLessons.add(EtyDToConverter.getLessonDto(lesson));
        });
        return dtoLessons;
    }

    @Override
    public boolean save(DtoLesson dtoLesson) throws SQLException {
        return lessonDAO.save(EtyDToConverter.getLessonEty(dtoLesson));
    }

    @Override
    public boolean update(DtoLesson dtoLesson) throws SQLException, IOException {
        return lessonDAO.update(EtyDToConverter.getLessonEty(dtoLesson));
    }

    @Override
    public boolean delete(DtoLesson dtoLesson) throws SQLException, IOException {
        return lessonDAO.delete(EtyDToConverter.getLessonEty(dtoLesson));
    }

    @Override
    public String getLastID() throws SQLException {
        try {
            String id = lessonDAO.getLastID();
            int intId = Integer.parseInt(id.replaceAll("\\D+", "")) + 1;
            return String.format("L" + "%03d", intId);
        } catch (Exception e) {
            e.printStackTrace();
            return "L001";
        }
    }

    @Override
    public boolean ifExit(DtoLesson dtoLesson) throws SQLException {
        return false;
    }


}
