package org.example.orm_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.orm_final.bo.BOFactory;
import org.example.orm_final.bo.utill.converter.DtoToTMConverter;
import org.example.orm_final.bo.custom.UserBO;
import org.example.orm_final.entity.user.DtoUser;
import org.example.orm_final.entity.user.DtoUserType;
import org.example.orm_final.view.user.TMUserType;
import org.example.orm_final.view.user.UserTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
    @FXML
    private TableColumn colID, colUName, colPassWord, colUType;
    @FXML
    private TableView<UserTM> tableView;
    @FXML
    private ComboBox cmbType;
    @FXML
    private TextField txtPassword, txtUserName;
    @FXML
    private Label lblID;
    @FXML
    private Button btnSave, btnUpdate, btnDelete;
    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.User);
    private String id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] colNames = {"Id","userType","userName","passWold"};
        TableColumn[] controls={colID,colUType,colUName,colPassWord};
        for (int i = 0; i <colNames.length ; i++) {
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
        lordTable();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void delete(){
        if(!lblID.getText().isEmpty()){
            try {
                DtoUser dtoUser=new DtoUser(Integer.parseInt(lblID.getText()),
                        cmbType.getValue().equals("ADMIN") ? DtoUserType.Admin : DtoUserType.Receptionist,
                        txtUserName.getText(), txtPassword.getText());
                boolean b=userBO.delete(dtoUser);
                new Alert(Alert.AlertType.INFORMATION,b?"deleted":"Failed").show();
                reLode();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    public void reLode() {
        try {
            id=userBO.getID();
            lblID.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cmbType.getItems().clear();
        cmbType.getItems().add("ADMIN");
        cmbType.getItems().add("Receptionist");
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        clearText();
        lordTable();
    }

    public void save() {
        if (!chackEmpty()) {
            try {
                DtoUser dtoUser=new DtoUser(Integer.parseInt(id),
                        cmbType.getValue().equals("ADMIN") ? DtoUserType.Admin : DtoUserType.Receptionist,
                        txtUserName.getText(), txtPassword.getText());
                boolean b = userBO.save(dtoUser);
                new Alert(Alert.AlertType.INFORMATION,b?"saved":"Failed").show();
                dtoUser.setId(Integer.parseInt(id));
                if(b){
                    tableView.getItems().add(DtoToTMConverter.getUserTM(dtoUser));
                }
                reLode();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    public boolean chackEmpty() {
        return txtPassword.getText().isEmpty() ? true : txtUserName.getText().isEmpty() ? true : cmbType.getValue() == null ? true : false;
    }

    public void clearText() {
        txtPassword.clear();
        txtUserName.clear();
        cmbType.setValue(null);
    }

    public void lordTable() {

        try {
            tableView.getItems().clear();
            userBO.getAll().forEach(dtoUser -> {
                tableView.getItems().add(DtoToTMConverter.getUserTM(dtoUser));
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void update(){
        if(!chackEmpty()){
            DtoUser dtoUser=new DtoUser(Integer.parseInt(lblID.getText()),
                    cmbType.getValue().equals("ADMIN") ? DtoUserType.Admin : DtoUserType.Receptionist,
                    txtUserName.getText(), txtPassword.getText());

            try {
                boolean b = userBO.update(dtoUser);
                if(b){
                    new Alert(Alert.AlertType.INFORMATION,"successfully Updated").show();
                    reLode();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    public void tableclicked(MouseEvent event) {
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        UserTM userTM=tableView.getSelectionModel().getSelectedItem();
        if(userTM!=null){
            lblID.setText(userTM.getId()+"");
            txtUserName.setText(userTM.getUserName());
            cmbType.setValue(userTM.getUserType().equals(TMUserType.Admin)?"ADMIN":"Receptionist");
        }
    }

    public void paneclicked(MouseEvent event) {
        reLode();
    }
}
