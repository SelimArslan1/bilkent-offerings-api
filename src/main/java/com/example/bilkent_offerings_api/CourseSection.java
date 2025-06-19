package com.example.bilkent_offerings_api;

public class CourseSection {

    private String sectionCode;
    private int capacity;
    private int enrolled;

    // Optional: add more fields later (like instructor, time, room, etc.)

    public CourseSection() {
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
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

    @Override
    public String toString() {
        return "Section{" +
                "sectionCode='" + sectionCode + '\'' +
                ", capacity=" + capacity +
                ", enrolled=" + enrolled +
                '}';
    }
}
