package org.example.orm_final.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {
    @FXML
    private AnchorPane temp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lordTempPages("user_managment.fxml");
    }

    public void lordUserManagement(ActionEvent actionEvent)  {
        lordTempPages("user_managment.fxml");
    }

    public void lordTempPages(String location){

        try {
            temp.getChildren().clear();
            AnchorPane newpane = FXMLLoader.load(getClass().getResource("/org/example/orm_final/FXML/" + location));
            temp.getChildren().add(newpane);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void lordInstructorManagement(ActionEvent actionEvent) {

    }
}
