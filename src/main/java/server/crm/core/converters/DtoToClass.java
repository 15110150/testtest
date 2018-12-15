package server.crm.core.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import server.crm.core.model.ClassDto;
import server.crm.entities.Class;
import server.crm.entities.Course;
import server.crm.entities.CourseCategory;
import server.crm.modules.courseclass.repositories.CourseRepository;

import java.util.Optional;

/**
 * @author: khoa1
 * @create: 13/11/2018
 */
@Component
public class DtoToClass implements Converter<ClassDto, Class> {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Class convert(ClassDto source) {
        if(source == null) return null;
        Class clazz = new Class();
        clazz.setId(source.getId());
        clazz.setName(source.getName());
        clazz.setDescription(source.getDescription());
        clazz.setRoom(source.getRoom());
        clazz.setNoStudent(source.getNoStudent());
        clazz.setOpenDate(source.getOpenDate());
        clazz.setEndDate(source.getEndDate());
        if(source.getCourse() != null && source.getCourse() != 0) {
            Optional<Course> parentTemp = courseRepository.findById(source.getCourse());
            clazz.setCourse(parentTemp.get());
        }
        else {
            clazz.setCourse(null);
        }
        clazz.setMarks(source.getMarks());
        clazz.setInvoices(source.getInvoices());
        clazz.setClassLecturers(source.getClassLecturers());
        clazz.setStatus(source.isStatus());
        return clazz;
    }
}
