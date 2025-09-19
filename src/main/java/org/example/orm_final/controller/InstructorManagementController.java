package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.custom.LessonBO;
import org.example.orm_final.bo.custom.impl.InstructorBOImpl;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.view.instructor.InstructorTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorManagementController implements Initializable {
    @FXML
    private TableColumn colID, colName, colEmail;
    @FXML
    private TableColumn<InstructorTM, List<VBox>> collessons;
    @FXML
    private TableView<InstructorTM> tableView;
    @FXML
    private TextField txtEmail, txtUserName;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete;
    private InstructorBO instructorBO = (InstructorBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.Instructor);
    private LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Lesson);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"Id", "name", "email"};
        TableColumn[] controls = {colID, colName, colEmail};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }
        collessons.setCellValueFactory(new PropertyValueFactory<>("lessons"));
        collessons.setCellFactory(col -> new TableCell<InstructorTM, List<VBox>>() {
            private final HBox hbox = new HBox(5); // use HBox if you want VBox items in a row

            @Override
            protected void updateItem(List<VBox> vboxes, boolean empty) {
                super.updateItem(vboxes, empty);
                if (empty || vboxes == null || vboxes.isEmpty()) {
                    setGraphic(null);
                } else {
                    hbox.getChildren().setAll(vboxes); // clear and add all VBoxes
                    setGraphic(hbox);
                }
            }
        });


//        colCourses.setCellFactory(col -> new TableCell<InstructorTM, List<HBox>>() {
//            @Override
//            protected void updateItem(List<HBox> item, boolean empty) {
//                super.updateItem(item, empty);
////                item.ge
//            }
//        });


        reLode();
        btnSave.setOnAction(event -> {
            save();
        });
        btnUpdate.setOnAction(event -> {
            update();
        });
        btnDelete.setOnAction(event -> {
            delete();
        });
        lordTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


    }

    public void delete() {
        if(!tableView.getSelectionModel().getSelectedItem().getId().isEmpty()){
            try {
                DtoInstructor dtoInstructor=new DtoInstructor();
                dtoInstructor.setId(tableView.getSelectionModel().getSelectedItem().getId());
                boolean b= instructorBO.delete(dtoInstructor);
                if(b){
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                }
                new Alert(Alert.AlertType.INFORMATION,b?"deleted":"Failed").show();
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    public void reLode() {
        tableView.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        try {
            id = instructorBO.getLastID();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        clearText();
    }

    public void save() {
        if (!chackEmpty()) {
            DtoInstructor dtoInstructor = new DtoInstructor();
            dtoInstructor.setName(txtUserName.getText());
            dtoInstructor.setEmail(txtEmail.getText());
            dtoInstructor.setId(lblID.getText());
            List<String> lessionIDs =new ArrayList<>();
            dtoInstructor.setLessons(lessionIDs);
            try {
                boolean b=instructorBO.save(dtoInstructor);
                if(b){
                    tableView.getItems().add(DtoToTMConverter.getInstructorTM(dtoInstructor));
                }
                new Alert(Alert.AlertType.INFORMATION,b?"saved":"Failed to save",ButtonType.OK).show();
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public boolean chackEmpty() {
        return txtUserName.getText().isEmpty() || txtEmail.getText().isEmpty();
    }

    public void clearText() {
        txtEmail.clear();
        txtUserName.clear();
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            instructorBO.getAll().forEach(dtoInstructor -> {
                tableView.getItems().add(DtoToTMConverter.getInstructorTM(dtoInstructor));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!chackEmpty()) {
            DtoInstructor dtoInstructor = new DtoInstructor(lblID.getText(),
                    txtUserName.getText(),
                    txtEmail.getText(), null);
            try {
                boolean b = instructorBO.update(dtoInstructor);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.getItems().add(DtoToTMConverter.getInstructorTM(dtoInstructor));
                    new Alert(Alert.AlertType.INFORMATION, "successfully Updated").show();
                    reLode();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }
        } else {

        }
    }

    public void tableclicked(MouseEvent event) {
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        InstructorTM instructorTM=tableView.getSelectionModel().getSelectedItem();
        if(instructorTM!=null){
            lblID.setText(instructorTM.getId()+"");
            txtUserName.setText(instructorTM.getName());
            txtEmail.setText(instructorTM.getEmail());
        }
    }

    public void paneclicked(MouseEvent event) {
        System.out.println("pane");
        reLode();
    }
}
