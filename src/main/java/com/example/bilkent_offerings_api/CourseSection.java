package com.example.bilkent_offerings_api;

public class CourseSection {

    private String courseCode;
    private int section;
    private String instructor;
    private int capacity;
    private int enrolled;
    private int available;

    public CourseSection() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(int enrolled) {
        this.enrolled = enrolled;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Section{" +
                "courseCode=" + courseCode +
                ", instructor=" + instructor +
                ", section='" + section +
                ", capacity=" + capacity +
                ", enrolled=" + enrolled +
                ", available=" + available +
                '}';
    }
}
