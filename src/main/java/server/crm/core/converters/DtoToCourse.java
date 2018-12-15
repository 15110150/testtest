package server.crm.core.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import server.crm.core.model.CourseDto;
import server.crm.entities.Course;
import server.crm.entities.CourseCategory;
import server.crm.modules.courseclass.repositories.CourseCategoryRepository;

import java.util.Optional;

/**
 * @author: khoa1
 * @create: 13/11/2018
 */
@Component
public class DtoToCourse implements Converter<CourseDto, Course> {
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Override
    public Course convert(CourseDto source) {
        if (source == null) return new Course();
        Course course = new Course();
        course.setId(source.getId());
        course.setName(source.getName());
        course.setDescription(source.getDescription());
        if(source.getCourseCategory() != null && source.getCourseCategory() != 0) {
            Optional<CourseCategory> parentTemp = courseCategoryRepository.findById(source.getCourseCategory());
            course.setCourseCategory(parentTemp.get());
        }
        else {
            course.setCourseCategory(null);
        }
        course.setContent(source.getContent());
        course.setMarketingCampaign(source.getMarketingCampaign());
        course.setLongTermCourse(source.isLongTermCourse());
        course.setClasses(source.getClasses());
        course.setStatus(source.isStatus());
        return course;
    }
}
