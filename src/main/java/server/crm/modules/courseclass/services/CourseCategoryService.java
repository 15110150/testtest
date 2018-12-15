package server.crm.modules.courseclass.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.core.model.CourseCategoryDto;
import server.crm.core.model.UpdateCategoryResponse;
import server.crm.entities.CourseCategory;

import java.util.List;

public interface CourseCategoryService {

    List<CourseCategory> findAllCourseCategory();

    Page<CourseCategory> findAllCourseCategoryPaginated(int page, int size, Sort sortBy);

    CourseCategory createCourseCategory(CourseCategory courseCategory);

    CourseCategory updateCourseCategory(CourseCategory courseCategory);

    CourseCategory deleteCourseCategory(String id);

    Page<CourseCategory> findAllCourseCategoryByKeyword(String keyword, int page, int size, Sort sortBy);

    CourseCategory findCourseCategoryById(String id);

    UpdateCategoryResponse genChildrenAndParent(CourseCategoryDto courseCategory, boolean isUpdateParent);

}
