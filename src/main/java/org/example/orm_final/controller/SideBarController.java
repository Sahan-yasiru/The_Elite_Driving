package org.example.orm_final.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.orm_final.bo.custom.impl.UserBOImpl;
import org.example.orm_final.model.user.DtoUserType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {
    @FXML
    private Button btnIns, btnUser;
    @FXML
    private AnchorPane temp;
    @FXML
    private Label userType;
    private UserBOImpl userBO = new UserBOImpl();
    private DtoUserType dtoUserType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lordTempPages("DashBord.fxml");
        userType.setText(UserBOImpl.getUserType());
        if (UserBOImpl.getUserType().equals("Receptionist")) {
            btnIns.setDisable(true);
            btnUser.setDisable(true);
        }


    }

    public void lordUserManagement(ActionEvent actionEvent) {
        lordTempPages("user_managment.fxml");
    }

    public void lordTempPages(String location) {

        try {
            temp.getChildren().clear();
            AnchorPane newpane = FXMLLoader.load(getClass().getResource("/org/example/orm_final/FXML/" + location));
            temp.getChildren().add(newpane);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void lordInstructorManagement(ActionEvent actionEvent) {
        lordTempPages("Instructor_managment.fxml");
    }

    public void CourseManagement(ActionEvent actionEvent) {
        lordTempPages("course_managment.fxml");
    }

    public void lessonManagement(ActionEvent actionEvent) {
        lordTempPages("lesson_managment.fxml");
    }

    public void StudentManagement(ActionEvent actionEvent) {
        lordTempPages("Student_managment.fxml");
    }

    public void lordHome(ActionEvent actionEvent) {
        lordTempPages("DashBord.fxml");
    }

    public void lordPaymentManagement(ActionEvent actionEvent) {
        lordTempPages("Payment_managment.fxml");
    }
    public void mouseEnterd(MouseEvent event) {
        if (event.getSource() instanceof ButtonBar) {
            ButtonBar bar = (ButtonBar) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), bar);
            scaleT.setToX(bar.getScaleX() + 0.1);
            scaleT.setToY(bar.getScaleY() + 0.1);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(2);
            glow.setHeight(2);
            glow.setRadius(2);
            bar.setEffect(glow);

        }
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(button.getScaleX() + 0.1);
            scaleT.setToY(button.getScaleY() + 0.1);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(2);
            glow.setHeight(2);
            glow.setRadius(2);
            button.setEffect(glow);

        }
    }

    public void mouseExited(MouseEvent event) {
        if (event.getSource() instanceof ButtonBar) {
            ButtonBar bar = (ButtonBar) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), bar);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            bar.setEffect(null);
        }
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), button);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            button.setEffect(null);
        }
    }


}
