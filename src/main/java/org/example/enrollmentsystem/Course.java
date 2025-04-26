package org.example.enrollmentsystem;

public class Course {
    private String courseId;
    private String courseName;
    private String department;
    private int credits;

    public Course(String courseId, String courseName, String department, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.department = department;
        this.credits = credits;
    }

    // Getters and toString()
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }

    @Override
    public String toString() {
        return courseId + " - " + courseName;
    }
}
