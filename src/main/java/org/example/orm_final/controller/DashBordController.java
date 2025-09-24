package org.example.orm_final.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.custom.LessonBO;
import org.example.orm_final.bo.custom.StudentBO;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {
    public Label numCou;
    public Label numIns;
    public Label numLes;
    public Label numStu;
    @FXML
    private Label clockFX,dateFX;
    private StudentBO studentBO=(StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);
    private InstructorBO instructorBO=(InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Instructor);
    private CourseBO courseBO=(CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private LessonBO lessonBO=(LessonBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Lesson);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            setTime();
            setNumbers();

    }

    private void setNumbers() {
        try {
            numCou.setText(courseBO.getNumOF());
            numStu.setText(studentBO.getNumOF());
            numIns.setText(instructorBO.getNumOF());
            numLes.setText(lessonBO.getNumOF());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("yyyy-MM-dd ");


        Date date=new Date();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            clockFX.setText(LocalDateTime.now().format(formatter));
            dateFX.setText(LocalDateTime.now().format(formatterD));
        }), new KeyFrame(Duration.seconds(1))); // Update every 1 second

        clock.setCycleCount(Timeline.INDEFINITE);

        clock.play();
    }
}
