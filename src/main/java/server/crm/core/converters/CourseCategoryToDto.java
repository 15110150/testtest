package server.crm.core.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import server.crm.core.model.CourseCategoryDto;
import server.crm.entities.CourseCategory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: khoa1
 * @create: 13/11/2018
 */
@Component
public class CourseCategoryToDto implements Converter<CourseCategory, CourseCategoryDto> {
    @Override
    public CourseCategoryDto convert(CourseCategory source) {
        if(source == null) return null;
        CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
        courseCategoryDto.setId(source.getId());
        courseCategoryDto.setChildren(source.getChildren());
        courseCategoryDto.setDescription(source.getDescription());
        courseCategoryDto.setName(source.getName());
        courseCategoryDto.setParent(source.getParent().getId());
        return courseCategoryDto;
    }
}
