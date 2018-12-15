package server.crm.core.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import server.crm.core.model.CourseCategoryDto;
import server.crm.entities.CourseCategory;
import server.crm.modules.courseclass.repositories.CourseCategoryRepository;

import java.util.Optional;

/**
 * @author: khoa1
 * @create: 13/11/2018
 */
@Component
public class DtoToCourseCategory implements Converter<CourseCategoryDto, CourseCategory> {
    @Autowired
    CourseCategoryRepository courseCategoryRepository;

    @Override
    public CourseCategory convert(CourseCategoryDto source) {
        if(source == null) return null;

        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setId(source.getId());
        courseCategory.setChildren(source.getChildren());
        courseCategory.setDescription(source.getDescription());
        courseCategory.setName(source.getName());
        if(source.getParent() != null && source.getParent() != 0) {
            Optional<CourseCategory> parentTemp = courseCategoryRepository.findById(source.getParent());
            courseCategory.setParent(parentTemp.get());
        }
        else {
            courseCategory.setParent(null);
        }
        courseCategory.setStatus(source.isStatus());
        return courseCategory;
    }
}
