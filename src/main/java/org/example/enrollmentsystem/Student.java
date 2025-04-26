package org.example.enrollmentsystem;
import java.time.LocalDate;

public class Student {
    private String studentId;
    private String fullName;
    private char gender;
    private LocalDate birthDate;
    private String email;
    private String department;
    private int year;

    public Student(String studentId, String fullName, char gender, LocalDate birthDate,
                   String email, String department, int year) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.department = department;
        this.year = year;
    }

    // Getters and toString()
    public String getStudentId() { return studentId; }
    public String getFullName() { return fullName; }

    @Override
    public String toString() {
        return studentId + " - " + fullName;
    }
}
