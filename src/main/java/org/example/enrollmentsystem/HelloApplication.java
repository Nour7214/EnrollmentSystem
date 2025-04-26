package org.example.enrollmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load FXML with correct path
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/enrollmentsystem/view/enrollment.fxml"));

        stage.setTitle("Student Enrollment System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}