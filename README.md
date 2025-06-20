# Bilkent Offerings API

A simple Spring Boot API that scrapes course offerings data from **Bilkent STARS** website and provides it in JSON format.

The scraper uses Selenium WebDriver to load the course list, click through interactive elements, and extract section details.

Scrapes real-time data from STARS and returns clean JSON of course offerings  

## API Endpoints

### `GET /api/{department}`

**Path Parameter:**

| Parameter   | Type   | Required | Description                     |
|-------------|--------|----------|---------------------------------|
| department  | String | Yes      | Department code (e.g., `CS`, `ME`, `EE`) |

**Query Parameters:**

| Parameter   | Type   | Required | Default   | Description                     |
|-------------|--------|----------|-----------|---------------------------------|
| courseCode  | String | No       | `""`      | Optional course code (e.g., `102`) — if provided, returns only sections for this course |
| section     | String | No       | `all`     | All sections are shown at default   |
| semester    | String | No       | `20243`   | Semester code — defaults to `20243` |

---

### Example Request

```
http GET /api/CS?courseCode=102&section=1&semester=20243
```

### Example Response

```json
[
  {
    "courseCode": "CS 102",
    "section": 1,
    "instructor": "John Doe",
    "capacity": 60,
    "enrolled": 15,
    "available": 45
  },
  {
    "courseCode": "CS 102",
    "section": 2,
    "instructor": "John Doe",
    "capacity": 50,
    "enrolled": 40,
    "available": 10
  }
]
```
### Notes
- Data source: https://stars.bilkent.edu.tr/homepage/offerings.php  
- If Bilkent STARS changes their HTML structure, scraping may need an update.
