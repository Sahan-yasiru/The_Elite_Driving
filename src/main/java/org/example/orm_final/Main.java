package org.example.orm_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/orm_final/FXML/Login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setResizable(false);
//        stage.setTitle("login");
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/SideBar.fxml"));
//        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setMinWidth(1252.00);
        stage.setMinHeight(716.00);
        stage.setResizable(true);
        stage.show();
    }
}