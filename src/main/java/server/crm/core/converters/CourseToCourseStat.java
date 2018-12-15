package server.crm.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import server.crm.core.model.CourseStat;
import server.crm.entities.Course;

/**
 * @author: khoa1
 * @create: 27/11/2018
 */
@Component
public class CourseToCourseStat implements Converter<Course, CourseStat> {
    @Override
    public CourseStat convert(Course source) {
        if (source == null) return new CourseStat();
        CourseStat course = new CourseStat();
        course.setId(source.getId());
        course.setName(source.getName());
        if(source.getClasses() != null){
            course.setNumberOfClasses(source.getClasses().size());
        }
        return course;
    }
}
