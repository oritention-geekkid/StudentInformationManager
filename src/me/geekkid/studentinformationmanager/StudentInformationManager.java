package me.geekkid.studentinformationmanager;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.geekkid.studentinformationmanager.student.Student;
import me.geekkid.studentinformationmanager.controller.MainController;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class StudentInformationManager extends Application {

    public static ConcurrentHashMap<Integer, Stage> stageManage = new ConcurrentHashMap<>();
    public static ObservableList<Student> students = FXCollections.observableArrayList();
    public static Image icon = new Image("me/geekkid/studentinformationmanager/image/icon.png");

    @Override
    public void start(Stage stage) throws Exception {
        File data = new File("students");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        stage.setTitle("Student Information Manager");
        stage.getIcons().add(icon);
        Pane rootLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout/StudentInformationManager.fxml")));
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stageManage.put(1,stage);

        loadStudents(data);

        MainController.maincontroller.loadTable(students);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    saveStudents(data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        MainController.maincontroller.searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                TableView<Student> table = MainController.maincontroller.table;
                ObservableList<Student> searchList = FXCollections.observableArrayList();
                if (newValue.equals("")) {
                    table.setItems(students);
                    table.refresh();
                } else {
                    for(Student stu : students) {
                        if (stu.getName().getValue().contains(newValue)) {
                            searchList.add(stu);
                        }
                    }
                    table.setItems(searchList);
                    table.refresh();
                }
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void loadStudents(File data) throws IOException {
        FileReader fileReader = new FileReader(data);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tmp = null;
        while ((tmp=bufferedReader.readLine())!=null) {
            int cnt = 0;
            String[] res = new String[6];
            while (tmp.contains("[")) {
                tmp = tmp.substring(tmp.indexOf("[")+1);
                res[cnt++] = tmp.substring(7,tmp.indexOf("]"));
            }
            students.add(new Student(Integer.parseInt(res[0]),res[1],res[2],Integer.parseInt(res[3]),res[4],res[5]));

        }
    }
    public static void saveStudents(File data) throws IOException {
        FileWriter fileWriter = new FileWriter(data);
        for(Student stu : students) {
            fileWriter.write(stu.toString());
            fileWriter.write('\n');
        }
        fileWriter.close();
    }
}
