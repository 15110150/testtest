package server.crm.modules.courseclass.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import server.crm.core.api.AuthenticationFacade;
import server.crm.core.converters.DtoToCourseCategory;
import server.crm.core.model.CourseCategoryDto;
import server.crm.core.model.LoggedUser;
import server.crm.core.model.UpdateCategoryResponse;
import server.crm.entities.CourseCategory;
import server.crm.modules.courseclass.services.CourseCategoryService;

import java.util.List;


@RestController
@RequestMapping("/api/v1.0/category")
public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DtoToCourseCategory converter;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public List<CourseCategory> getAllCourseCategories() {
        return courseCategoryService.findAllCourseCategory();
    }

    // TODO: get all candidates for children and all candidates for parent
    // POST: /api/v1.0/category/changeCurrentCategory?isUpdateParent= true || false
    @PostMapping(value = "/changeCurrentCategory", params = {"isUpdateParent"})
    public UpdateCategoryResponse genChildrenAndParents(@RequestBody CourseCategoryDto courseCategory, @Param("isUpdateParent") boolean isUpdateParent) {
        return courseCategoryService.genChildrenAndParent(courseCategory, isUpdateParent);
    }

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<CourseCategory> getAllCourseCategory(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return courseCategoryService.findAllCourseCategoryPaginated(page, size, Sort.by(sortBy));
    }

    @GetMapping(value = "find", params = {"page", "size", "sortBy", "keyword"})
    public Page<CourseCategory> getAllCourseCategoryByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return courseCategoryService.findAllCourseCategoryByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public CourseCategory getDetailCourseCategory(
            @RequestParam("id") String id) {
        return courseCategoryService.findCourseCategoryById(id);
    }

//    @PostMapping
//    public CourseCategory createCourseCategory(
//            @RequestBody CourseCategory courseCategory) {
//        return courseCategoryService.createCourseCategory(courseCategory);
//    }

    @PostMapping
    public CourseCategory createCourseCategory(
            @RequestBody CourseCategoryDto courseCategory) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        CourseCategory category = converter.convert(courseCategory);
        return courseCategoryService.createCourseCategory(category);
    }

    @PutMapping
    public CourseCategory updateCourseCategory(
            @RequestBody CourseCategoryDto courseCategory) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        CourseCategory category = converter.convert(courseCategory);
        return courseCategoryService.updateCourseCategory(category);
    }

    @DeleteMapping(params = {"id"})
    public CourseCategory deleteCourseCategory(
            @RequestParam("id") String id) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        return courseCategoryService.deleteCourseCategory(id);
    }
}
