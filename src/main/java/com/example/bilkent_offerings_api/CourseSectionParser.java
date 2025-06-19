package com.example.bilkent_offerings_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseSectionParser {

    public CourseSection parseRow(WebElement row) {

        List<WebElement> columns = row.findElements(By.tagName("td"));

        if (columns.size() < 5) {
            throw new IllegalArgumentException("Unexpected number of columns in section row.");
        }

        CourseSection section = new CourseSection();
        String sectionCode = columns.get(0).getText().trim();
        String courseCode = sectionCode.substring(0, sectionCode.indexOf('-'));

        sectionCode = sectionCode.substring(sectionCode.indexOf('-') + 1);

        section.setInstructor(columns.get(1).getText().trim());
        section.setCourseCode(courseCode);
        try {
            section.setSection(parseInteger(sectionCode));
            section.setCapacity(parseInteger(columns.get(3).getText().trim()));
            section.setEnrolled(parseInteger(columns.get(6).getText().trim()));
            section.setAvailable(parseInteger(columns.get(8).getText().trim()));
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse capacity/enrolled for section " + section.getSection());
            section.setCapacity(0);
            section.setEnrolled(0);
        }

        return section;
    }

    private int parseInteger(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}

