package org.example.orm_final.bo.utill.converter;

import javafx.scene.layout.VBox;
import org.example.orm_final.entity.user.DtoUser;
import org.example.orm_final.entity.user.DtoUserType;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.view.CourseTM;
import org.example.orm_final.view.instructor.InstructorTM;
import org.example.orm_final.view.instructor.InstuctorLBL;
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
        List<VBox> courses = new ArrayList<>();
        if(dtoInstructor.getLessons()!=null){
        dtoInstructor.getLessons().forEach(courseID -> {
            InstuctorLBL instuctorLbl=new InstuctorLBL(courseID);
            VBox vBox=new VBox(instuctorLbl);
            courses.add(vBox);
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
}
