package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.custom.CourseBO;
import org.example.orm_final.bo.custom.PaymentBO;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.model.DtoPayment;
import org.example.orm_final.view.PaymentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentManagementController implements Initializable {
    @FXML
    private TableColumn colID, colAmount, colDate, colStu_id;
    @FXML
    private TableView<PaymentTM> tableView;
    @FXML
    private ListView stuList;
    @FXML
    private TextField txtAmount, txtStudentID;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete, btnRest, btnStuIDsShow;
    private PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Payment);
    private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Course);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"paymentId", "amount", "date", "student_ID"};
        TableColumn[] controls = {colID, colAmount, colDate, colStu_id};
        for (int i = 0; i < colNames.length; i++) {
            controls[i].setCellValueFactory(new PropertyValueFactory<>(colNames[i]));
        }
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
        btnStuIDsShow.setOnAction(event -> {
            lordCosIDs();
        });
        btnRest.setOnAction(event -> {
            reLode();
        });
        lordTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void lordCosIDs() {
        stuList.getItems().clear();
        if (!txtStudentID.getText().isEmpty()) {
            List<String> couIDs = courseBO.getCouJoinWithStuID(txtStudentID.getText());
            if (couIDs != null && !couIDs.isEmpty()) {
                stuList.getItems().addAll(couIDs);
            } else {
                txtStudentID.setStyle(txtStudentID.getStyle() + "-fx-border-color: red;");
            }
        } else {
            txtStudentID.setStyle(txtStudentID.getStyle() + "-fx-border-color: red;");

        }
    }

    public void delete() {
        if (!lblID.getText().isEmpty()) {
            try {
                DtoPayment dtoPayment = new DtoPayment();

                dtoPayment.setPaymentId(lblID.getText());
                dtoPayment.setAmount(Double.parseDouble(txtAmount.getText()));
                dtoPayment.setStudent(txtStudentID.getText());
                dtoPayment.setDate(LocalDate.now());

                boolean b = paymentBO.delete(dtoPayment);
                if (b) {
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    new Alert(Alert.AlertType.INFORMATION, "deleted").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Delete failed").show();
                }
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    public void reLode() {
        try {
            id=paymentBO.getLastID();
            lblID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        clearText();
        lordTable();
    }

    public void save() {
        if (!chackEmpty()) {
            try {
                DtoPayment dtoPayment = new DtoPayment();

                dtoPayment.setPaymentId(lblID.getText());
                dtoPayment.setAmount(Double.parseDouble(txtAmount.getText()));
                dtoPayment.setStudent(txtStudentID.getText());
                dtoPayment.setDate(LocalDate.now());

                boolean b = paymentBO.save(dtoPayment);
                new Alert(Alert.AlertType.INFORMATION, b ? "saved" : "Failed").show();
                if (b) {
                    tableView.getItems().add(DtoToTMConverter.getPaymentTM(dtoPayment));
                }
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    public boolean chackEmpty() {
        TextField [] textFields = {txtAmount, txtStudentID};
        boolean empty = false;
        for (TextField textField : textFields) {
            if (textField.getText().trim().isEmpty()) {
                empty = true;
                textField.setStyle(textField.getStyle() + "-fx-border-color: red;");
            }
        }
        try {
            Double.parseDouble(txtAmount.getText());
        }catch (NumberFormatException e){
            empty = true;
            txtAmount.setStyle(txtAmount.getStyle() + "-fx-border-color: red;");

        }
        boolean b=stuList.getSelectionModel().getSelectedItems().isEmpty();
        if(b){
            empty = true;
            stuList.setStyle(stuList.getStyle() + "-fx-border-color: red;");
        }
        return empty;
    }

    public void clearText() {
        txtAmount.clear();
        txtStudentID.clear();
        stuList.getItems().clear();
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            paymentBO.getAll().forEach(dtoPayment -> {
                tableView.getItems().add(DtoToTMConverter.getPaymentTM(dtoPayment));
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void update() {
        if (!chackEmpty()) {
            DtoPayment dtoPayment = new DtoPayment();

            dtoPayment.setPaymentId(lblID.getText());
            dtoPayment.setAmount(Double.parseDouble(txtAmount.getText()));
            dtoPayment.setStudent(txtStudentID.getText());
            dtoPayment.setDate(LocalDate.now());

            try {
                boolean b = paymentBO.update(dtoPayment);
                if (b) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Updated").show();

                    int selIndex = tableView.getSelectionModel().getSelectedIndex();
                    if (selIndex >= 0) {
                        tableView.getItems().set(selIndex, DtoToTMConverter.getPaymentTM(dtoPayment));
                    } else {
                        lordTable();
                    }
                    reLode();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Update failed").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    public void tableclicked(MouseEvent event) {
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        PaymentTM paymentTM = tableView.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            lblID.setText(paymentTM.getPaymentId() + "");
            txtStudentID.setText(paymentTM.getStudent_ID());
            txtAmount.setText(paymentTM.getAmount() + "");
            stuList.getItems().clear();
            if (!txtStudentID.getText().isEmpty()) {
                List<String> couIDs = courseBO.getCouJoinWithStuID(txtStudentID.getText());
                if (couIDs != null && !couIDs.isEmpty()) {
                    stuList.getItems().addAll(couIDs);
                }
            }
        }
    }

    public void paneclicked(MouseEvent event) {
//        reLode();
    }
}
