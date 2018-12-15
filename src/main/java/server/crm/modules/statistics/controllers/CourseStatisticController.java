package server.crm.modules.statistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Course;
import server.crm.modules.statistics.services.CourseStatisticService;

import java.util.Map;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
@RestController
@RequestMapping("/api/v1.0/statistic")
public class CourseStatisticController {

    @Autowired
    private CourseStatisticService courseStatisticService;

    @GetMapping("/course/mostStudent")
    public Course getMostPopularCourse() {
        return courseStatisticService.getMostStudentCourses();
    }

    @GetMapping(value = "/course/newCreated", params = {"year"})
    public int getNewCreatedInYear(@RequestParam("year") String year) {
        return 0;
    }

    @GetMapping(value = "/course", params = {"year"})
    public Map<String, Object> getYearStatistic(@RequestParam("year") String year) {
        return courseStatisticService.getStatisticByYear(year);
    }
}
