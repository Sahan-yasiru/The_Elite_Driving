package org.example.orm_final.bo.utill.converter;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.user.DtoUser;
import org.example.orm_final.model.user.DtoUserType;
import org.example.orm_final.view.instructor.InstructorTM;
import org.example.orm_final.view.instructor.InstuctorLBL;
import org.example.orm_final.view.user.TMUserType;
import org.example.orm_final.view.user.UserTM;

import java.awt.*;
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
        instructorTM.setNic(dtoInstructor.getNic());
        List<VBox> courses = new ArrayList<>();
        dtoInstructor.getCourses().forEach(course -> {
            InstuctorLBL instuctorLbl=new InstuctorLBL(course.getId());
            VBox vBox=new VBox(instuctorLbl);
            courses.add(vBox);
        });
        instructorTM.setCourses(courses);

        return instructorTM;
    }


}
