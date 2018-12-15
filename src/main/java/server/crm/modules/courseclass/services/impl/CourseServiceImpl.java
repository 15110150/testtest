package server.crm.modules.courseclass.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Course;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.courseclass.repositories.CourseRepository;
import server.crm.modules.courseclass.services.CourseService;

import java.util.Date;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Course> findAllCourseCategoryPaginated(int page, int size, Sort sortBy) {
        return courseRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public Course createCourse(Course course) {
        course.setStatus(true);
        course.setCreatedBy(LoggedUser.get());
        course.setCreatedDate(new Date());
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        try {
            Optional<Course> target = courseRepository.findById(course.getId());
            if (target.isPresent()) {
                course.setCreatedDate(target.get().getCreatedDate());
                course.setCreatedBy(target.get().getCreatedBy());
                course.setUpdatedBy(LoggedUser.get());
                course.setUpdatedDate(new Date());
                return courseRepository.save(course);
            }
            throw new ApiRuntimeException("Can't find course");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save course");
        }
    }

    @Override
    public Course deleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        course.setUpdatedBy(LoggedUser.get());
        course.setUpdatedDate(new Date());
        course.setStatus(false);
        return courseRepository.save(course);
    }

    @Override
    public Page<Course> findAllCourseByKeyword(String keyword, int page, int size, Sort sortBy) {
        return courseRepository.findByNameContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Page<Course> findAllCourseByCategory(Long categoryId,
                                                Boolean longTerm,
                                                String keyword,
                                                int page, int size, Sort sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, sortBy);
        if (categoryId == null && longTerm == null && keyword == null) {
            return courseRepository.findAllByStatusIsTrue(pageRequest);
        } else if (categoryId == null && longTerm == null) { // keyword
            return courseRepository.findByKeyWord(pageRequest, true, keyword, keyword);
        } else if (longTerm == null && keyword == null) { // category
            return courseRepository.findAllByCourseCategory_IdAndStatusIsTrue(pageRequest, categoryId);
        } else if (categoryId == null && keyword == null) { // longterm
            return courseRepository.findAllByLongTermCourseAndStatusIsTrue(pageRequest, longTerm);
        } else if (categoryId == null) { //longterm, keword
            return courseRepository.findByLongTermAndKeyWord(pageRequest, true, longTerm, keyword, keyword);
        } else if (longTerm == null) { //keyword, category
            return courseRepository.findByCourseCategoryAndKeyWord(pageRequest, true, categoryId, keyword, keyword);
        } else if (keyword == null) { //longterm, category
            return courseRepository.findAllByLongTermCourseAndCourseCategory_IdAndStatusIsTrue(pageRequest, longTerm, categoryId);
        } else
            return courseRepository.findByCourseCategoryLongTermAndKeyWord(pageRequest, true, categoryId, longTerm, keyword, keyword);
    }
}
