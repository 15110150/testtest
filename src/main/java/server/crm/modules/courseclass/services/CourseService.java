package server.crm.modules.courseclass.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.Course;

public interface CourseService {

    Page<Course> findAllCourseCategoryPaginated(int page, int size, Sort sortBy);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Course deleteCourse(Long id);

    Page<Course> findAllCourseByKeyword(String keyword, int page, int size, Sort sortBy);

    Course findCourseById(Long id);

    Page<Course> findAllCourseByCategory(Long categoryId, Boolean longTerm, String keyword, int page, int size, Sort sortBy);
}