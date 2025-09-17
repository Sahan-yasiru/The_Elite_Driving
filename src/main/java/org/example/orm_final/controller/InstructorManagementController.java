package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.custom.impl.CourseBOImpl;
import org.example.orm_final.bo.custom.impl.InstructorBOImpl;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.view.CourseTM;
import org.example.orm_final.view.instructor.InstructorTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorManagementController implements Initializable {
    @FXML
    private TableColumn colID, colName, colNic;
    @FXML
    private TableColumn<InstructorTM, List<VBox>> colCourses;
    @FXML
    private TableView<InstructorTM> tableView;
    @FXML
    private ListView<String> couList;
    @FXML
    private TextField txtNIC, txtUserName;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete;
    private InstructorBO instructorBO = (InstructorBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.Instructor);
    private CourseBO courseBO = (CourseBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        couList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String[] colNames = {"Id", "name", "nic"};
        TableColumn[] controls = {colID, colName, colNic};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }
        colCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
        colCourses.setCellFactory(col -> new TableCell<InstructorTM, List<VBox>>() {
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
        btnSave.setDisable(false);
        couList.getItems().clear();
        try {
            id = instructorBO.getLastID();
            courseBO.getAll().forEach(dtoCourse -> {
                couList.getItems().add(dtoCourse.getId());
            });
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
            dtoInstructor.setNic(txtNIC.getText());
            dtoInstructor.setId(lblID.getText());
            List<DtoCourse> dtoCourses=new ArrayList<>();
            for (String selectedItem : couList.getSelectionModel().getSelectedItems()) {
                DtoCourse dtoCourse=new DtoCourse();
                if(selectedItem.isEmpty()){
                    continue;
                }
                dtoCourse.setId(selectedItem);
                dtoCourses.add(dtoCourse);
            }
            dtoInstructor.setCourses(dtoCourses);
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
        return txtUserName.getText().isEmpty() || txtNIC.getText().isEmpty() || couList.getSelectionModel().getSelectedItems() == null;
    }

    public void clearText() {
        txtNIC.clear();
        txtUserName.clear();
        couList.getSelectionModel().getSelectedItems().clear();
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
                    txtNIC.getText(), null);
            List<DtoCourse> dtoCourses = new ArrayList<>();
            couList.getSelectionModel().getSelectedItems().forEach(selectedItem -> {
                DtoCourse dtoCourse = new DtoCourse();
                dtoCourse.setId(selectedItem);
                dtoCourses.add(dtoCourse);
            });
            dtoInstructor.setCourses(dtoCourses);
            try {
                boolean b = instructorBO.update(dtoInstructor);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.getItems().add(DtoToTMConverter.getInstructorTM(dtoInstructor));
                    new Alert(Alert.AlertType.INFORMATION, "successfully Updated").show();
                    reLode();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {

        }
    }

    public void tableclicked(MouseEvent event) {
        couList.getSelectionModel().clearSelection();
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        InstructorTM instructorTM=tableView.getSelectionModel().getSelectedItem();
        if(instructorTM!=null){
            lblID.setText(instructorTM.getId()+"");
            txtUserName.setText(instructorTM.getName());
            txtNIC.setText(instructorTM.getNic());
            instructorTM.getCourses().forEach(vBox -> {
                vBox.getChildren().forEach(node -> {
                    if (node instanceof Label li) {
                        String courseId = li.getText().trim();
                        if (couList.getItems().contains(courseId)) {
                            couList.getSelectionModel().select(courseId);
                        }
                    }
                });
            });
        }
    }

    public void paneclicked(MouseEvent event) {
        reLode();
    }
}
