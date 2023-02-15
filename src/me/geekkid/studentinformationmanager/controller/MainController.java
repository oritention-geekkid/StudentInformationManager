package me.geekkid.studentinformationmanager.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import me.geekkid.studentinformationmanager.StudentInformationManager;
import me.geekkid.studentinformationmanager.student.Student;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static me.geekkid.studentinformationmanager.StudentInformationManager.*;

public class MainController implements Initializable {
    public static MainController maincontroller;

    @FXML public TableView<Student> table;
    @FXML public TextField searchField;
    @FXML private TableColumn<Student, Integer> idCol;
    @FXML private TableColumn<Student, String> nameCol;
    @FXML private TableColumn<Student, String> sexCol;
    @FXML private TableColumn<Student, Integer> ageCol;
    @FXML private TableColumn<Student, String> phoneNumberCol;
    @FXML private TableColumn<Student, String> addressCol;

    public void loadTable(ObservableList<Student> students) {
        idCol.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getName());
        sexCol.setCellValueFactory(cellData -> cellData.getValue().getSex());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().getAge().asObject());
        phoneNumberCol.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        table.setItems(students);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maincontroller = this;
    }

    public void openAddUI(ActionEvent actionEvent) throws IOException {
        Stage addStage = new Stage();
        addStage.setTitle("Add a student");
        addStage.setAlwaysOnTop(true);
        Pane addLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../layout/AddStage.fxml")));
        Scene addUI = new Scene(addLayout);
        addStage.setScene(addUI);
        addStage.getIcons().add(icon);

        StudentInformationManager.stageManage.put(2,addStage);

        addStage.show();
    }

    public void importStudents(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a Source File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT","*.txt"));
        File data = fileChooser.showOpenDialog(stageManage.get(1));
        StudentInformationManager.loadStudents(data);
        table.refresh();
    }


    public void exportStudents(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Location");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT","*.txt"));
        File save = fileChooser.showSaveDialog(stageManage.get(1));
        StudentInformationManager.saveStudents(save);
    }

    public void removeStudent(ActionEvent actionEvent) {
        Student stu = table.getSelectionModel().getSelectedItem();
        students.remove(stu);
        table.refresh();
    }

    public void openModifyUI(ActionEvent actionEvent) throws IOException {
        Student stu = table.getSelectionModel().getSelectedItem();
        Stage modifyStage = new Stage();
        modifyStage.setTitle("Modify a student");
        modifyStage.setAlwaysOnTop(true);
        Pane modifyLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../layout/ModifyStage.fxml")));
        Scene modifyUI = new Scene(modifyLayout);
        modifyStage.setScene(modifyUI);
        ModifyController.modifyController.initModifyUI(stu);
        modifyStage.getIcons().add(icon);

        StudentInformationManager.stageManage.put(3,modifyStage);

        modifyStage.show();
    }
}
