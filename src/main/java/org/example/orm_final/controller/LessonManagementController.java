package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.InstructorBO;
import org.example.orm_final.bo.custom.LessonBO;
import org.example.orm_final.bo.custom.StudentBO;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.model.DtoStudent;
import org.example.orm_final.view.LessonTM;
import org.example.orm_final.view.instructor.InstuctorLBL;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class LessonManagementController implements Initializable {
    @FXML
    private ComboBox cmbStuIDs, timeHR, timeMI, timeAMPM, cmbCourIDs, cmbInsIDs;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableColumn colID, colDate, colName, colTime;
    @FXML
    private TableColumn<LessonTM, InstuctorLBL> colCourID, colInsID, colStuID;
    @FXML
    private TableView<LessonTM> tableView;
    @FXML
    private TextField lesName;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete;
    private LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Lesson);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Instructor);
    private StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"lessonId", "date", "lessonName", "time"};
        TableColumn[] controls = {colID, colDate, colName, colTime};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }
        lordCMBS();
        colCourID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourID.setCellFactory(col -> new TableCell<LessonTM, InstuctorLBL>() {
            @Override
            protected void updateItem(InstuctorLBL lbl, boolean empty) {
                super.updateItem(lbl, empty);
                if (empty || lbl.getText().isEmpty()) {
                    setGraphic(null);
                } else {
                    setGraphic(lbl);
                }
            }
        });

        colInsID.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colInsID.setCellFactory(col -> new TableCell<LessonTM, InstuctorLBL>() {
            @Override
            protected void updateItem(InstuctorLBL lbl, boolean empty) {
                super.updateItem(lbl, empty);
                if (empty || lbl.getText().isEmpty()) {
                    setGraphic(null);
                } else {
                    setGraphic(lbl);
                }
            }
        });


        colStuID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colStuID.setCellFactory(col -> new TableCell<LessonTM, InstuctorLBL>() {
            @Override
            protected void updateItem(InstuctorLBL lbl, boolean empty) {
                super.updateItem(lbl, empty);
                if (empty ||lbl.getText().isEmpty()) {
                    setGraphic(null);
                } else {
                    setGraphic(lbl);
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
        lordTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return formatter.format(object);
                } else {
                    return null;
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }

        });
        datePicker.getEditor().setDisable(true);
        datePicker.getEditor().setOpacity(1);


    }

    private void lordCMBS() {
        cmbCourIDs.getItems().clear();
        cmbInsIDs.getItems().clear();
        cmbStuIDs.getItems().clear();
        try {
            courseBO.getAll().forEach(dtoCourse -> {
                cmbCourIDs.getItems().add(dtoCourse.getId());
            });

            instructorBO.getAll().forEach(dtoInstructor -> {
                cmbInsIDs.getItems().add(dtoInstructor.getId());
            });

            studentBO.getAll().forEach(dtoStudent -> {
                cmbStuIDs.getItems().add(dtoStudent.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        timeHR.getItems().clear();
        for (int i = 1; i <= 12; i++) {
            timeHR.getItems().add(i);
        }

        timeMI.getItems().clear();
        for (int i = 0; i <= 59; i++) {
            if (i < 10) {
                timeMI.getItems().add("0" + i);
            } else {
                timeMI.getItems().add(i);
            }
        }

        timeAMPM.getItems().clear();
        timeAMPM.getItems().add("AM");
        timeAMPM.getItems().add("PM");
    }

    public void delete() {
        if(!tableView.getSelectionModel().getSelectedItem().getLessonId().isEmpty()){
            try {
                DtoLesson dtoLesson =new DtoLesson();
                dtoLesson.setLessonId(tableView.getSelectionModel().getSelectedItem().getLessonId());
                boolean b= lessonBO.delete(dtoLesson);
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
        Control[] controls = {lesName, datePicker, timeAMPM, timeMI, timeHR, timeAMPM, cmbCourIDs, cmbInsIDs, cmbStuIDs};
        for (Control control : controls) {
            control.setStyle(control.getStyle() + "-fx-border-color: white;");
        }
        btnSave.setDisable(false);
        tableView.getSelectionModel().clearSelection();
        try {
            id = lessonBO.getLastID();
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
            DtoLesson dtoLesson = new DtoLesson();
            dtoLesson.setLessonId(lblID.getText());
            dtoLesson.setLessonName(lesName.getText());
            dtoLesson.setTime(timeHR.getValue() + " : " + timeMI.getValue() + " " + timeAMPM.getValue());
            dtoLesson.setDate(datePicker.getValue());

            DtoInstructor instructor = new DtoInstructor();
            instructor.setId(cmbInsIDs.getValue().toString());
            dtoLesson.setInstructor(instructor);

            DtoCourse dtoCourse = new DtoCourse();
            dtoCourse.setId(cmbCourIDs.getValue().toString());
            dtoLesson.setCourse(dtoCourse);

            DtoStudent dtoStudent = new DtoStudent();
            dtoStudent.setId(cmbStuIDs.getValue().toString());
            dtoLesson.setStudent(dtoStudent);

            try {
                boolean b = lessonBO.save(dtoLesson);
                if (b) {
                    tableView.getItems().add(DtoToTMConverter.getLessonTM(dtoLesson));
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

        if (datePicker.getValue() != null && timeHR.getValue() != null && timeMI.getValue() != null && timeAMPM.getValue() != null
                && cmbCourIDs.getValue() != null && cmbInsIDs.getValue() != null && cmbStuIDs.getValue() != null && !(lesName.getText().isEmpty())) {
            return false;
        } else {
            Control[] controls = {lesName, datePicker, timeAMPM, timeMI, timeHR, timeAMPM, cmbCourIDs, cmbInsIDs, cmbStuIDs};
            for (Control item : controls) {
                if (item instanceof TextField tx && tx.getText().isEmpty()) {
                    item.setStyle(item.getStyle() + "-fx-border-color: red;");

                } else if (item instanceof DatePicker picker && picker.getValue() == null) {
                    item.setStyle(item.getStyle() + "-fx-border-color: red;");
                } else if (item instanceof ComboBox cmbCourIDs && cmbCourIDs.getValue() == null) {
                    item.setStyle(item.getStyle() + "-fx-border-color: red;");
                }
            }
            return true;
        }
    }


    public void clearText() {
        datePicker.setValue(null);
        timeAMPM.setValue(null);
        timeMI.setValue(null);
        timeHR.setValue(null);
        lesName.clear();
        cmbCourIDs.getSelectionModel().clearSelection();
        cmbInsIDs.getSelectionModel().clearSelection();
        cmbStuIDs.getSelectionModel().clearSelection();
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            lessonBO.getAll().forEach(dtoLesson -> {
                tableView.getItems().add(DtoToTMConverter.getLessonTM(dtoLesson));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (! chackEmpty()) {
            DtoLesson dtoLesson = new DtoLesson();
            dtoLesson.setLessonId(lblID.getText());
            dtoLesson.setLessonName(lesName.getText());
            dtoLesson.setTime(timeHR.getValue() + " : " + timeMI.getValue() + " " + timeAMPM.getValue());
            dtoLesson.setDate(datePicker.getValue());

            DtoInstructor instructor = new DtoInstructor();
            instructor.setId(cmbInsIDs.getValue().toString());
            dtoLesson.setInstructor(instructor);

            DtoCourse dtoCourse = new DtoCourse();
            dtoCourse.setId(cmbCourIDs.getValue().toString());
            dtoLesson.setCourse(dtoCourse);

            DtoStudent dtoStudent = new DtoStudent();
            dtoStudent.setId(cmbStuIDs.getValue().toString());
            dtoLesson.setStudent(dtoStudent);
            try {
                boolean b = lessonBO.update(dtoLesson);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.getItems().add(DtoToTMConverter.getLessonTM(dtoLesson));
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
        LessonTM lessonTM=tableView.getSelectionModel().getSelectedItem();
        if(lessonTM!=null){
            lblID.setText(lessonTM.getLessonId());
            lesName.setText(lessonTM.getLessonName());
            datePicker.setValue(lessonTM.getDate());
            timeHR.setValue(lessonTM.getTime().substring(0,2));
            timeMI.setValue(lessonTM.getTime().substring(4,7));
            timeAMPM.setValue(lessonTM.getTime().substring(7));


            cmbCourIDs.setValue(lessonTM.getCourseID().getText());
            cmbStuIDs.setValue(lessonTM.getStudentID().getText());
            cmbInsIDs.setValue(lessonTM.getInstructorID().getText());

        }
    }


    public void spaceclicked(MouseEvent event) {
        reLode();
    }

}
