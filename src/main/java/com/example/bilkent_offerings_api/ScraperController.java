package com.example.bilkent_offerings_api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ScraperController {

    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/{department}")
    public List<CourseSection> getSections(@PathVariable String department,
                                           @RequestParam(defaultValue = "cs102") String courseCode,
                                           @RequestParam(defaultValue = "20243") String semester) {
        return scraperService.scrapeSections(courseCode, department, semester);
    }
}

