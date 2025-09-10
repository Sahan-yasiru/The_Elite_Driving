package org.example.orm_final;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.dao.util.BCryptHashing;
import org.example.orm_final.dao.util.FactoryConfiguration;
import org.example.orm_final.model.DtoUser;
import org.example.orm_final.model.DtoUserType;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
////        launch();
//        try {
//            Session session = FactoryConfiguration.getInstance().getSession();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
        UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.User);

        try {
//            userBO.save(new DtoUser("A003", DtoUserType.Admin,"yasiru3","yasiru000"));
            boolean b=BCryptHashing.chackHashedPassword("yasiru000",BCryptHashing.getHashedPassword("yasiru000"));
            System.out.println(b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}