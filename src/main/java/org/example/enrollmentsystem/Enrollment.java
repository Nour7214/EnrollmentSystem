package org.example.enrollmentsystem;

public class Enrollment {
    private final String studentName;
    private final String courseName;

    public Enrollment(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }
}

