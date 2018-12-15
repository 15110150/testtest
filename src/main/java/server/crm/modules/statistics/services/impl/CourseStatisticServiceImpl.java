package server.crm.modules.statistics.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.crm.core.converters.CourseToCourseStat;
import server.crm.core.model.CourseStat;
import server.crm.entities.Course;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.courseclass.repositories.CourseRepository;
import server.crm.modules.statistics.repositories.CourseStatisticRepository;
import server.crm.modules.statistics.services.CourseStatisticService;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
@Service
public class CourseStatisticServiceImpl implements CourseStatisticService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStatisticRepository courseStatisticRepository;

    @Autowired
    private CourseToCourseStat converter;

    @Override
    public Map<String, Object> getStatisticByYear(String year) {
        LocalDate yearDate = LocalDate.now();
        try {
            Integer yearInt = Integer.valueOf(year);
            yearDate = LocalDate.of(yearInt, Month.JANUARY, 1);
        } catch (Exception e) {
            throw new ApiRuntimeException("Parse year throws an exception!");
        }
        Map<String, Object> resultMap =  new HashMap<>();
        resultMap.put("thisYear", this.getCourseStatInYear(yearDate));
        resultMap.put("lastYear", this.getCourseStatInYear(yearDate.minusYears(1)));
        return resultMap;
    }

    @Override
    public Course getMostStudentCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses.stream()
                .max(Comparator.comparing(course ->
                        course.getClasses().stream()
                                .mapToInt(c -> c.getNoStudent())
                                .sum()))
                .get();
    }

    private Map<String, Object> getCourseStatInYear(LocalDate yearDate){
        Date startDayOfYear = java.sql.Date.valueOf(LocalDate.of(yearDate.getYear(), Month.JANUARY, 1));
        Date endDayOfYear = java.sql.Date.valueOf(LocalDate.of(yearDate.getYear(), Month.DECEMBER, 31));
        return parseDataToResponse(
                courseStatisticRepository.getCourseHaveClassInYear(startDayOfYear, endDayOfYear)
        );
    }

/*
    trả về:
        - số lượng khoá học được tạo trong năm
        - danh sách 10 khoá học có nhiều học sinh đăng ký nhất: id, tên khoá, số lớp, số học viên
        - tương tự với năm trước
         */
    private Map<String, Object> parseDataToResponse(List<Course> courses) {
        int numberOfCouses = courses.size();
        List<Course> listMost10Course = courses.stream()
                .sorted(Comparator.comparing((course) -> course.getClasses().size()))
                .collect(Collectors.toList());
        if(listMost10Course.size() > 10) {
            listMost10Course = listMost10Course.subList(0, 10);
        }

        List<CourseStat> courseStats = new ArrayList<>();
        listMost10Course.forEach(course -> courseStats.add(converter.convert(course)));
        Collections.reverse(courseStats);

        Map<String, Object> yearStatResponse = new HashMap<>();
        yearStatResponse.put("numberOfCouses", numberOfCouses);
        yearStatResponse.put("listMost10Course", courseStats);
        return yearStatResponse;
    }


}
