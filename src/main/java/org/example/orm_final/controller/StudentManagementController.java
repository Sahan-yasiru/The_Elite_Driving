package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.StudentBO;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.model.DtoStudent;
import org.example.orm_final.view.StudentTM;
import org.example.orm_final.view.label.TMLBL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class StudentManagementController implements Initializable {
    public ListView colList;
    @FXML
    private TableColumn colID, colName, colEmail, colTel;
    @FXML
    private TableColumn<StudentTM, List<TMLBL>> colCourses;
    @FXML
    private TableView<StudentTM> tableView;
    @FXML
    private TextField txtEmail, txtName, txtPhoneNum;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete,btnRest;
    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"Id", "name", "email", "phoneNumber"};
        TableColumn[] controls = {colID, colName, colEmail, colTel};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }

        colCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
        colCourses.setCellFactory(col -> new TableCell<StudentTM, List<TMLBL>>() {
            VBox vBox = new VBox(5);
            @Override
            protected void updateItem(List<TMLBL> tmlbls, boolean empty) {
                super.updateItem(tmlbls, empty);
                if (empty || tmlbls.isEmpty()) {
                    setGraphic(null);
                } else {
                    tmlbls.forEach(vBox.getChildren()::add);
                    setGraphic(vBox);
                }
            }
        });

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
        colList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lordCourseIDs();


    }

    public void lordCourseIDs() {
        colList.getItems().clear();
        try {
            courseBO.getAll().forEach(dtoCourse -> {
                colList.getItems().add(dtoCourse.getId());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        if(!tableView.getSelectionModel().getSelectedItem().getId().isEmpty()){
            try {
                DtoStudent dtoStudent=new DtoStudent();
                dtoStudent.setId(tableView.getSelectionModel().getSelectedItem().getId());
                boolean b= studentBO.delete(dtoStudent);
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
        Control[] controls = {txtEmail, txtPhoneNum, txtName, colList};
        for (Control control : controls) {
            control.setStyle(control.getStyle() + "-fx-border-color: white;");
        }
        tableView.getSelectionModel().clearSelection();
        btnSave.setDisable(false);
        try {
            id = studentBO.getLastID();
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
            DtoStudent dtoStudent = new DtoStudent();
            dtoStudent.setName(txtName.getText());
            dtoStudent.setEmail(txtEmail.getText());
            dtoStudent.setId(lblID.getText());
            dtoStudent.setPhoneNumber(Integer.parseInt(txtPhoneNum.getText()));

            dtoStudent.setCourses(new ArrayList<>());
            colList.getSelectionModel().getSelectedItems().forEach(item -> {
                dtoStudent.getCourses().add(item.toString());
            });
            try {
                boolean b = studentBO.save(dtoStudent);
                if (b) {
                    tableView.getItems().add(DtoToTMConverter.getStudentTM(dtoStudent));
                }
                new Alert(Alert.AlertType.INFORMATION, b ? "saved" : "Failed to save", ButtonType.OK).show();
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public boolean chackEmpty() {
        if (!txtName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtPhoneNum.getText().isEmpty() && !colList.getSelectionModel().getSelectedItems().isEmpty()) {
            boolean b = Pattern.matches("^0[0-9]{9}$", txtPhoneNum.getText());
            boolean b1 = Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", txtEmail.getText());
            if (b && b1) {
                return false;
            }
            if (!b) {
                txtPhoneNum.setStyle(txtPhoneNum.getStyle() + "-fx-border-color: red;");
            }
            if (!b1) {
                txtEmail.setStyle(txtEmail.getStyle() + "-fx-border-color: red;");
            }
        } else {
            Control[] controls = {txtEmail, txtPhoneNum, txtName, colList};
            for (Control control : controls) {
                if (control instanceof ListView<?> listView) {
                    if (listView.getSelectionModel().getSelectedItems().isEmpty()) {
                        listView.setStyle(listView.getStyle() + "-fx-border-color: red;");
                    } else if (control instanceof TextField tx && tx.getText().isEmpty()) {
                        tx.setStyle(tx.getStyle() + "-fx-border-color: red;");
                    }
                }
            }
        }
        return true;
    }

    public void clearText() {
        txtEmail.clear();
        txtName.clear();
        txtPhoneNum.clear();
        colList.getSelectionModel().clearSelection();
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            studentBO.getAll().forEach(dtoStudent -> {
                tableView.getItems().add(DtoToTMConverter.getStudentTM(dtoStudent));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!chackEmpty()) {
            DtoStudent dtoStudent = new DtoStudent();
            dtoStudent.setName(txtName.getText());
            dtoStudent.setEmail(txtEmail.getText());
            dtoStudent.setId(lblID.getText());
            dtoStudent.setPhoneNumber(Integer.parseInt(txtPhoneNum.getText()));

            dtoStudent.setCourses(new ArrayList<>());
            colList.getSelectionModel().getSelectedItems().forEach(item -> {
                dtoStudent.getCourses().add(item.toString());
            });
            try {
                boolean b = studentBO.update(dtoStudent);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.getItems().add(DtoToTMConverter.getStudentTM(dtoStudent));
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
        StudentTM studentTM =tableView.getSelectionModel().getSelectedItem();
        if(studentTM !=null){
            lblID.setText(studentTM.getId());
            txtName.setText(studentTM.getName());
            txtEmail.setText(studentTM.getEmail());
            txtPhoneNum.setText(studentTM.getPhoneNumber()+"");
            colList.getSelectionModel().select(studentTM.getCourses());
        }
    }

    public void paneclicked(MouseEvent event) {
//        System.out.println("pane");
//        reLode();
    }

}
