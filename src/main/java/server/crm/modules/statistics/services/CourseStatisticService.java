package server.crm.modules.statistics.services;

import server.crm.entities.Course;

import java.util.List;
import java.util.Map;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
public interface CourseStatisticService {
    Map<String, Object> getStatisticByYear(String year);

    Course getMostStudentCourses();
}
