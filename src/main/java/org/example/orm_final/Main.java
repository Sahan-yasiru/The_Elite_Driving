package org.example.orm_final;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.hibernate.*;
import org.hibernate.Cache;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.stat.Statistics;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/orm_final/FXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/SideBar.fxml"));
////        Stage stage = new Stage();
//        stage.setScene(new Scene(fxmlLoader.load()));
//        stage.setMinWidth(1400.00);
//        stage.setMinHeight(716.00);
//        stage.setResizable(true);
//        stage.show();
    }


}