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
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    private ComboBox studentsChoiceBox;
    @FXML
    private ComboBox coursesChoiceBox;
    @FXML
    private Button enroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File f1 = new File("students.txt");
        try {
            Scanner s = new Scanner(f1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onHelloButtonClick() {

    }
}