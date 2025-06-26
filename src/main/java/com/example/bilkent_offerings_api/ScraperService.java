package com.example.bilkent_offerings_api;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private WebDriver driver;
    private final CourseSectionParser sectionParser;

    public ScraperService(CourseSectionParser sectionParser) {
        this.sectionParser = sectionParser;
    }

    private void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--ignore-certificate-errors");

        // Check if running in Docker
        String chromeDriver = System.getenv("CHROME_DRIVER");
        String chromeBin = System.getenv("CHROME_BIN");

        if (chromeDriver != null && chromeBin != null) {
            // Running in Docker
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-debugging-port=9222");
            options.setBinary(chromeBin);
            System.setProperty("webdriver.chrome.driver", chromeDriver);
        } else {
            // Running locally
            WebDriverManager.chromedriver().setup();
        }

        this.driver = new ChromeDriver(options);
    }

    public List<CourseSection> scrapeSections(String courseCode, String department, String semester, String section) {
        List<CourseSection> sections = new ArrayList<>();
        initDriver();

        int sectionCode = Integer.parseInt(section);

        try {
            String url = String.format("https://stars.bilkent.edu.tr/homepage/offerings.php?COURSE_CODE=%s&SEMESTER=%s", department, semester);
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> courses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("tr[doclick='doOnClickCourse']")));


            if(!courseCode.isEmpty()) {
                for (WebElement course : courses) {
                    String id = course.getAttribute("id");

                    if(id.equals(courseCode)) {

                        course.click();

                        break;
                    }
                }
            }
            List<WebElement> courseRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.cssSelector("tr[doclick='doOnClickSection']")));

            for (WebElement courseRow : courseRows) {
                try {

                    CourseSection newSection = sectionParser.parseRow(courseRow);

                    if(newSection.getSection() == sectionCode || sectionCode == 0) {

                        sections.add(newSection);
                        if(sectionCode != 0) break; // To exit looping the sections when only one section is wanted
                    }

                } catch (TimeoutException e) {
                    System.err.println("Timeout while waiting for section rows.");
                } catch (Exception e) {
                    System.err.println("Error while scraping a course: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Scraping failed: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        System.out.println("Course information sent.");
        return sections;
    }
}

