package me.geekkid.studentinformationmanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.geekkid.studentinformationmanager.student.Student;

import static me.geekkid.studentinformationmanager.StudentInformationManager.stageManage;
import static me.geekkid.studentinformationmanager.StudentInformationManager.students;

public class AddController {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField sexField;
    @FXML private TextField ageField;
    @FXML private TextField pnField;
    @FXML private TextField adField;
    @FXML private Label Notice;

    public void closeUI(ActionEvent actionEvent) {
        stageManage.get(2).close();
    }

    public void submitData(ActionEvent actionEvent) {
        try {
            String name, sex, phoneNumber, address;
            Integer id = Integer.parseInt(idField.getText());
            Integer age = Integer.parseInt(ageField.getText());
            name = nameField.getText();
            sex = sexField.getText();
            phoneNumber = pnField.getText();
            address = adField.getText();
            Student stu = new Student(id,name,sex,age,phoneNumber,address);
            students.add(stu);
            stageManage.get(2).close();
            MainController.maincontroller.table.refresh();
        } catch (Exception e) {
            Notice.setText("Error! Invalid Data Input.");
        }
    }
}
