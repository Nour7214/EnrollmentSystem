package org.example.enrollmentsystem;

public class Enrollment {
    private String studentId;
    private String courseId;
    private String semester;

    public Enrollment(String studentId, String courseId, String semester) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
    }

    // Getters and toString()
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public String getSemester() { return semester; }

    @Override
    public String toString() {
        return studentId + "|" + courseId + "|" + semester;
    }
}
