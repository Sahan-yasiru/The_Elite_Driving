package org.example.orm_final.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.entity.user.DtoUser;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController{


    public PasswordField txtPassword;
    public TextField txtUserName;
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.User);

    public void btnLogin(ActionEvent actionEvent) throws SQLException, IOException {

        if (!(txtPassword.getText().isEmpty() && txtUserName.getText().isEmpty())) {
            DtoUser dtoUser = new DtoUser();
            dtoUser.setUserName(txtUserName.getText());
            dtoUser.setPassWold(txtPassword.getText());
            System.out.println(userBO.ifExit(dtoUser));
            try {
                if (userBO.ifExit(dtoUser)) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/SideBar.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(fxmlLoader.load()));
                    stage.setMinWidth(1500.00);
                    stage.setMinHeight(716.00);
                    stage.setResizable(true);
                    stage.show();

                    Stage currentWindow = (Stage) txtPassword.getScene().getWindow();
                    currentWindow.close();
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


}
