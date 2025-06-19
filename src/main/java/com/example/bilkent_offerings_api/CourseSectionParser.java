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

        // Adjust indices based on actual column order of your table
        section.setSectionCode(columns.get(0).getText().trim());

        try {
            section.setCapacity(parseInteger(columns.get(3).getText().trim()));
            section.setEnrolled(parseInteger(columns.get(6).getText().trim()));
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse capacity/enrolled for section " + section.getSectionCode());
            section.setCapacity(0);
            section.setEnrolled(0);
        }

        // Add more fields if needed, e.g. instructor, schedule, room, etc.

        return section;
    }

    private int parseInteger(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}

