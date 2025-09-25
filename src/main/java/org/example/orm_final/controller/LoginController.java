package org.example.orm_final.controller;

import javafx.animation.ScaleTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.model.user.DtoUser;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController{


    public PasswordField txtPassword;
    public TextField txtUserName;
    public Button btn;
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.User);

    public void btnLogin(ActionEvent actionEvent) throws SQLException, IOException {

        if (!(txtPassword.getText().isEmpty() && txtUserName.getText().isEmpty())) {
            DtoUser dtoUser = new DtoUser();
            dtoUser.setUserName(txtUserName.getText());
            dtoUser.setPassWold(txtPassword.getText());
            System.out.println(userBO.ifExit(dtoUser));
            try {
                if (userBO.ifExit(dtoUser)) {
                    Stage currentWindow = (Stage) txtPassword.getScene().getWindow();
                    currentWindow.setScene(new Scene(
                            new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/LoadinScreen.fxml")).load()
                    ));
                    currentWindow.centerOnScreen();
                    currentWindow.show();

                    Task<Scene> loadingTask = new Task<>() {
                        @Override
                        protected Scene call() throws Exception {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/SideBar.fxml"));
                            return new Scene(fxmlLoader.load());
                        }
                    };

                    loadingTask.setOnSucceeded(event -> {
                        Scene value = loadingTask.getValue();
                        currentWindow.setMinWidth(1400.00);
                        currentWindow.setMinHeight(716.00);
                        currentWindow.setResizable(true);
                        currentWindow.setScene(value);
                        currentWindow.centerOnScreen();
                    });

                    loadingTask.setOnFailed(event -> {
                        System.out.println("Fail to load application");
                    });

                    new Thread(loadingTask).start();
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/SideBar.fxml"));
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(fxmlLoader.load()));
//                    stage.setMinWidth(1400.00);
//                    stage.setMinHeight(716.00);
//                    stage.setResizable(true);
//                    stage.show();
//
//                    Stage currentWindow = (Stage) txtPassword.getScene().getWindow();
//                    currentWindow.close();
                } else {
                   setborder();
                }
            }
                catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }

        }
        setborder();
    }

    public void lordAddUser(ActionEvent actionEvent) {
    }
    public void setborder(){
        txtPassword.setStyle(txtPassword.getStyle() + "-fx-border-color: #ff00ec;");
        txtUserName.setStyle(txtUserName.getStyle() + "-fx-border-color: #ff00ec;");

    }


    public void mouseEnterd(MouseEvent event) {
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
