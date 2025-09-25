package org.example.orm_final.bo.utill.converter;

import org.example.orm_final.model.*;
import org.example.orm_final.model.user.DtoUser;
import org.example.orm_final.model.user.DtoUserType;
import org.example.orm_final.view.CourseTM;
import org.example.orm_final.view.LessonTM;
import org.example.orm_final.view.PaymentTM;
import org.example.orm_final.view.StudentTM;
import org.example.orm_final.view.instructor.InstructorTM;
import org.example.orm_final.view.label.TMLBL;
import org.example.orm_final.view.user.TMUserType;
import org.example.orm_final.view.user.UserTM;

import java.util.ArrayList;
import java.util.List;

public class DtoToTMConverter {
    public static UserTM getUserTM(DtoUser dtoUser){
        UserTM userTM=new UserTM();
        userTM.setId(dtoUser.getId());
        userTM.setUserName(dtoUser.getUserName());
        userTM.setPassWold(dtoUser.getPassWold());
        userTM.setUserType(dtoUser.getUserType().equals(DtoUserType.Admin)? TMUserType.Admin:TMUserType.Receptionist);

        return userTM;
    }
    public static DtoUser getUserDto(UserTM userTM){
        DtoUser dtoUser =new DtoUser();
        dtoUser.setId(userTM.getId());
        dtoUser.setUserName(userTM.getUserName());
        dtoUser.setPassWold(userTM.getPassWold());
        dtoUser.setUserType(userTM.getUserType().equals(TMUserType.Admin)? DtoUserType.Admin:DtoUserType.Receptionist);

        return dtoUser;
    }
    public static InstructorTM getInstructorTM(DtoInstructor dtoInstructor){
        InstructorTM instructorTM=new InstructorTM();
        instructorTM.setId(dtoInstructor.getId());
        instructorTM.setName(dtoInstructor.getName());
        instructorTM.setEmail(dtoInstructor.getEmail());
        List<TMLBL> courses = new ArrayList<>();
        if(dtoInstructor.getLessons()!=null){
        dtoInstructor.getLessons().forEach(courseID -> {
            TMLBL instuctorLbl=new TMLBL(courseID);
            courses.add(instuctorLbl);
        });
        instructorTM.setLessons(courses);
        }

        return instructorTM;
    }


    public static CourseTM getCourseTM(DtoCourse dtoCourse) {
        CourseTM courseTM=new CourseTM();
        courseTM.setId(dtoCourse.getId());
        courseTM.setDescription(dtoCourse.getDescription());
        courseTM.setFree(dtoCourse.getFree());
        courseTM.setDuration(dtoCourse.getDuration());

        return courseTM;

    }

    public static LessonTM getLessonTM(DtoLesson dtoLesson) {
        LessonTM lessonTM=new LessonTM();
        lessonTM.setLessonId(dtoLesson.getLessonId());
        lessonTM.setLessonName(dtoLesson.getLessonName());
        lessonTM.setTime(dtoLesson.getTime());
        lessonTM.setDate(dtoLesson.getDate());

        lessonTM.setCourseID(new TMLBL(dtoLesson.getCourse().getId()));
        lessonTM.setInstructorID(new TMLBL(dtoLesson.getInstructor().getId()));
        lessonTM.setStudentID(new TMLBL(dtoLesson.getStudent().getId()));

        return lessonTM;

    }

    public static StudentTM getStudentTM(DtoStudent dtoStudent) {
        StudentTM studentTM=new StudentTM();
        studentTM.setId(dtoStudent.getId());
        studentTM.setName(dtoStudent.getName());
        studentTM.setEmail(dtoStudent.getEmail());
        studentTM.setPhoneNumber(dtoStudent.getPhoneNumber());

        List<TMLBL> courses = new ArrayList<>();
        dtoStudent.getCourses().forEach(courseID -> {
            courses.add(new TMLBL(courseID));
        });
        studentTM.setCourses(courses);

        return studentTM;


    }

    public static PaymentTM getPaymentTM(DtoPayment dtoPayment) {
        PaymentTM paymentTM=new PaymentTM();
        paymentTM.setPaymentId(dtoPayment.getPaymentId());
        paymentTM.setAmount(dtoPayment.getAmount());
        paymentTM.setDate(dtoPayment.getDate());
        paymentTM.setStudent_ID(dtoPayment.getStudent());

        return paymentTM;
    }
}
