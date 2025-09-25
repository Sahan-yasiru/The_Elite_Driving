package org.example.orm_final;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import javafx.application.Application;
import javafx.concurrent.Task;
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
        stage.setScene(new Scene(
                new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/LoadinScreen.fxml")).load()
        ));
        stage.centerOnScreen();
        stage.show();

        Task<Scene> loadingTask = new Task<>() {
            @Override
            protected Scene call() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/orm_final/FXML/Login.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };

        loadingTask.setOnSucceeded(event -> {
            Scene value = loadingTask.getValue();

            stage.setTitle("Login");
            stage.setScene(value);
            stage.setResizable(false);
            stage.centerOnScreen();
        });

        loadingTask.setOnFailed(event -> {
            System.out.println("Fail to load application");
        });

        new Thread(loadingTask).start();
    }


}