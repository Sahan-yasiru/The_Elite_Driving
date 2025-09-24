package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.impl.CourseBOImpl;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.view.CourseTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CourseManagementController implements Initializable {
    @FXML
    private TableColumn colID, cloDescription, colDuration,colFree;
    @FXML
    private TableView<CourseTM> tableView;
    @FXML
    private TextField txtDuration, txtFree,txtDescription;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete,btnRest;
    private CourseBO courseBO = (CourseBOImpl) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"Id", "description", "duration", "free"};
        TableColumn[] controls = {colID, cloDescription, colDuration, colFree};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }

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
        btnRest.setOnAction(event -> {
            reLode();
        });
        lordTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


    }

    public void delete() {
        if(!tableView.getSelectionModel().getSelectedItem().getId().isEmpty()){
            try {
                DtoCourse dtoCourse =new DtoCourse();
                dtoCourse.setId(tableView.getSelectionModel().getSelectedItem().getId());
                boolean b= courseBO.delete(dtoCourse);
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
        Control[] controls={txtDescription, txtFree, txtDuration};
        for (Control control : controls) {
            control.setStyle(control.getStyle()+"-fx-border-color: white;");
        }
        btnSave.setDisable(false);
        tableView.getSelectionModel().clearSelection();
        try {
            id = courseBO.getLastID();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        clearText();
    }

    public void save() {
        boolean b1= chackEmpty();
        System.out.println(b1);
        if (b1) {
            DtoCourse dtoCourse = new DtoCourse();
            dtoCourse.setId(lblID.getText());
            dtoCourse.setFree(Double.parseDouble(txtFree.getText()));
            dtoCourse.setDescription(txtDescription.getText());
            dtoCourse.setDuration(txtDuration.getText());

            try {
                boolean b=courseBO.save(dtoCourse);
                if(b){
                    tableView.getItems().add(DtoToTMConverter.getCourseTM(dtoCourse));
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
        if( txtDescription.getText().isEmpty() || txtDuration.getText().isEmpty() ||txtFree.getText().isEmpty() ) {
            Control[] controls={txtDescription, txtFree, txtDuration};
            for (Control control : controls) {
                control.setStyle(control.getStyle()+"-fx-border-color: red;");
            }
            return false;
        }
        try {
            Double d=Double.parseDouble(txtDuration.getText());
        }catch (NumberFormatException e){
            txtFree.setStyle(txtFree.getStyle()+"-fx-border-color: red;");
            return false;
        }
        return true;
    }

    public void clearText() {
        txtDescription.clear();
        txtDuration.clear();
        txtFree.clear();
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            courseBO.getAll().forEach(dtoCourse -> {
                tableView.getItems().add(DtoToTMConverter.getCourseTM(dtoCourse));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (chackEmpty()) {
            DtoCourse dtoCourse = new DtoCourse(lblID.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtFree.getText()), txtDescription.getText(),null);
            try {
                boolean b = courseBO.update(dtoCourse);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.getItems().add(DtoToTMConverter.getCourseTM(dtoCourse));
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
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        CourseTM courseTM=tableView.getSelectionModel().getSelectedItem();
        if(courseTM!=null){
            lblID.setText(courseTM.getId());
            txtDescription.setText(courseTM.getDescription());
            txtFree.setText(courseTM.getFree()+"");
            txtDuration.setText(courseTM.getDuration());
        }
    }



    public void spaceclicked(MouseEvent event) {
//        reLode();
    }
}
