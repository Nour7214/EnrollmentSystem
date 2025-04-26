module org.example.enrollmentsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.enrollmentsystem to javafx.fxml;
    exports org.example.enrollmentsystem;
}