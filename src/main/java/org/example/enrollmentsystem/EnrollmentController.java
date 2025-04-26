package org.example.enrollmentsystem;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.enrollmentsystem.Course;
import org.example.enrollmentsystem.Enrollment;
import org.example.enrollmentsystem.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentController {
    @FXML private ComboBox<Student> studentComboBox;
    @FXML private ComboBox<Course> courseComboBox;
    @FXML private ComboBox<String> semesterComboBox;
    @FXML private TextArea enrollmentsTextArea;
    @FXML private Button enrollButton;

    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();

    private static final String STUDENTS_FILE = "D:\\EL ZOOZ JAVA\\EnrollmentSystem\\src\\main\\resources\\org\\example\\enrollmentsystem\\students";
    private static final String COURSES_FILE = "D:\\EL ZOOZ JAVA\\EnrollmentSystem\\src\\main\\resources\\org\\example\\enrollmentsystem\\courses";
    private static final String ENROLLMENTS_FILE = "enrollments.txt";

    @FXML
    public void initialize() {
        // Initialize semester options
        semesterComboBox.getItems().addAll("Spring2025", "Fall2025", "Summer2025");
        semesterComboBox.setValue("Spring2025");

        // Load data from files
        loadStudents();
        loadCourses();
        loadEnrollments();

        // Set up combo boxes
        studentComboBox.setItems(students);
        courseComboBox.setItems(courses);

        // Display current enrollments
        updateEnrollmentsDisplay();
    }

    private void loadStudents() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(STUDENTS_FILE));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    Student student = new Student(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim().charAt(0),
                            LocalDate.parse(parts[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            parts[4].trim(),
                            parts[5].trim(),
                            Integer.parseInt(parts[6].trim())
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            showAlert("Error", "Could not load students: " + e.getMessage());
        }
    }

    private void loadCourses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(COURSES_FILE));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    Course course = new Course(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            Integer.parseInt(parts[3].trim())
                    );
                    courses.add(course);
                }
            }
        } catch (IOException e) {
            showAlert("Error", "Could not load courses: " + e.getMessage());
        }
    }

    private void loadEnrollments() {
        try {
            if (Files.exists(Paths.get(ENROLLMENTS_FILE))) {
                List<String> lines = Files.readAllLines(Paths.get(ENROLLMENTS_FILE));
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        Enrollment enrollment = new Enrollment(
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim()
                        );
                        enrollments.add(enrollment);
                    }
                }
            }
        } catch (IOException e) {
            showAlert("Error", "Could not load enrollments: " + e.getMessage());
        }
    }

    @FXML
    private void handleEnroll() {
        Student selectedStudent = studentComboBox.getValue();
        Course selectedCourse = courseComboBox.getValue();
        String selectedSemester = semesterComboBox.getValue();

        if (selectedStudent == null || selectedCourse == null || selectedSemester == null) {
            showAlert("Error", "Please select both a student and a course");
            return;
        }

        // Check for duplicate enrollment
        for (Enrollment e : enrollments) {
            if (e.getStudentId().equals(selectedStudent.getStudentId()) &&
                    e.getCourseId().equals(selectedCourse.getCourseId()) &&
                    e.getSemester().equals(selectedSemester)) {
                showAlert("Warning", "This student is already enrolled in this course for the selected semester");
                return;
            }
        }

        // Create new enrollment
        Enrollment newEnrollment = new Enrollment(
                selectedStudent.getStudentId(),
                selectedCourse.getCourseId(),
                selectedSemester
        );

        // Add to list and file
        enrollments.add(newEnrollment);
        saveEnrollmentToFile(newEnrollment);
        updateEnrollmentsDisplay();

        // Show confirmation
        showAlert("Success", "Student enrolled successfully!");
    }

    private void saveEnrollmentToFile(Enrollment enrollment) {
        try (FileWriter fw = new FileWriter(ENROLLMENTS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(enrollment.toString());
        } catch (IOException e) {
            showAlert("Error", "Could not save enrollment: " + e.getMessage());
        }
    }

    private void updateEnrollmentsDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Enrollments:\n");
        sb.append("Student ID | Course ID | Semester\n");
        sb.append("--------------------------------\n");

        for (Enrollment e : enrollments) {
            sb.append(e.getStudentId()).append(" | ")
                    .append(e.getCourseId()).append(" | ")
                    .append(e.getSemester()).append("\n");
        }

        enrollmentsTextArea.setText(sb.toString());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}