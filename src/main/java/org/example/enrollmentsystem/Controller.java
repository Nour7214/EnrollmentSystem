package org.example.enrollmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    protected ArrayList<String>studentNames = new ArrayList<>();
    protected ArrayList<String>courses = new ArrayList<>();

    @FXML
    private ComboBox studentsChoiceBox;
    @FXML
    private ComboBox coursesChoiceBox;
    @FXML
    private Button enroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File f1 = new File("src/main/java/org/example/enrollmentsystem/students");
        File f2 = new File("src/main/java/org/example/enrollmentsystem/courses");
        try {
            Scanner s = new Scanner(f1);
            while(s.hasNext()){
                String line = s.nextLine();
                studentNames.add(line);
            }
            s.close();
            s = new Scanner(f2);
            while(s.hasNext()){
                String course = s.nextLine();
                courses.add(course);
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.studentsChoiceBox.getItems().addAll(studentNames);
        this.coursesChoiceBox.getItems().addAll(courses);

    }
    @FXML
    protected void onHelloButtonClick() {

    }
}