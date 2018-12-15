package server.crm.modules.courseclass.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.core.api.AuthenticationFacade;
import server.crm.core.converters.DtoToCourse;
import server.crm.core.model.CourseDto;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Course;
import server.crm.modules.courseclass.services.CourseService;

@RestController
@RequestMapping("/api/v1.0/course")
public class CourseController {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DtoToCourse converter;

    @GetMapping(value = "search", params = {"page", "size", "sortBy", "keyword"})
    public Page<Course> getAllCourseByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return courseService.findAllCourseByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"page", "size", "sortBy", "categoryId", "longTerm", "keyword"})
    public Page<Course> getAllCourseByParameters(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(required = false, name = "longTerm") Boolean longTerm,
            @RequestParam("keyword") String keyword) {
        return courseService.findAllCourseByCategory(categoryId, longTerm, keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public Course getDetailCourse(
            @RequestParam("id") Long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping
    public Course createCourse(
            @RequestBody CourseDto course) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        Course courseEntity = converter.convert(course);
        return courseService.createCourse(courseEntity);
    }

    @PutMapping
    public Course updateCourse(
            @RequestBody CourseDto course) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        Course courseEntity = converter.convert(course);
        return courseService.updateCourse(courseEntity);
    }

    @DeleteMapping(params = {"id"})
    public Course deleteCourse(
            @RequestParam("id") Long id) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        return courseService.deleteCourse(id);
    }
}
