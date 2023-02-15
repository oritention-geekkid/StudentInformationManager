package me.geekkid.studentinformationmanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.geekkid.studentinformationmanager.student.Student;

import java.net.URL;
import java.util.ResourceBundle;

import static me.geekkid.studentinformationmanager.StudentInformationManager.stageManage;
import static me.geekkid.studentinformationmanager.StudentInformationManager.students;

public class ModifyController implements Initializable {
    public static ModifyController modifyController;

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField sexField;
    @FXML private TextField ageField;
    @FXML private TextField pnField;
    @FXML private TextField adField;
    @FXML private Label Notice;

    public void closeUI(ActionEvent actionEvent) {
        stageManage.get(3).close();
    }

    public void initModifyUI (Student stu) {
        idField.setText(String.valueOf(stu.getId().getValue()));
        nameField.setText(stu.getName().getValue());
        sexField.setText(stu.getSex().getValue());
        ageField.setText(String.valueOf(stu.getAge().getValue()));
        pnField.setText(stu.getPhoneNumber().getValue());
        adField.setText(stu.getAddress().getValue());
    }

    public void submitData(ActionEvent actionEvent) {
        try {
            Student modifyingStu = MainController.maincontroller.table.getSelectionModel().getSelectedItem();
            modifyingStu.setId(Integer.parseInt(idField.getText()));
            modifyingStu.setAge(Integer.parseInt(ageField.getText()));
            modifyingStu.setName(nameField.getText());
            modifyingStu.setSex(sexField.getText());
            modifyingStu.setPhoneNumber(pnField.getText());
            modifyingStu.setAddress(adField.getText());
            stageManage.get(3).close();
            MainController.maincontroller.table.refresh();
        } catch (Exception e) {
            Notice.setText("Error! Invalid Data Input.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyController = this;
    }
}
