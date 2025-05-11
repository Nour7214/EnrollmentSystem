package org.example.enrollmentsystem;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    protected ArrayList<String>studentNames = new ArrayList<>();
    protected ArrayList<String>courses = new ArrayList<>();
    @FXML
    private Label FailureText;
    @FXML
    private TableView<Enrollment> tableView = new TableView<>();

    @FXML
    private TableColumn<Enrollment, String> studentCol = new TableColumn<>();

    @FXML
    private TableColumn<Enrollment, String> courseCol = new TableColumn<>();

    @FXML
    private ComboBox studentsChoiceBox;
    @FXML
    private ComboBox coursesChoiceBox;
    @FXML
    private Button enroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));

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
    protected void enrollAction(ActionEvent event)throws IOException {
        FailureText.setVisible(false);
        String student = (String) studentsChoiceBox.getValue();
        System.out.println(student);
        String course = (String) coursesChoiceBox.getValue();
        System.out.println(course);
        FileWriter fw = new FileWriter("src/main/java/org/example/enrollmentsystem/out.txt", true);
        PrintWriter p = new PrintWriter(fw);
        File f = new File("src/main/java/org/example/enrollmentsystem/out.txt");
        Scanner s1 = new Scanner(f);
        while (s1.hasNext()) {
            String search = s1.nextLine();
            System.out.println(search);
            if (Objects.equals(student + "," + course, search)) {
                FailureText.setVisible(true);
                break;
            }
        }
        s1.close();
        System.out.println("Finish");
        if (!FailureText.isVisible()){
            p.println(student + "," + course);
            p.close();
            System.out.println("done");
        }

        s1 = new Scanner(f);
        ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();

        while(s1.hasNext()){
                s1.useDelimiter(",");
                String name2 = s1.next();
                name2 = name2.replaceAll("\n","");
                System.out.println(name2);
                s1.useDelimiter("\n");
                String coursename = s1.next();
                coursename=coursename.replaceAll(",","");
                System.out.println(coursename);
                enrollments.add(new Enrollment(name2,coursename));
        }
       tableView.setItems(enrollments);
        System.out.println("done2");
   }

}