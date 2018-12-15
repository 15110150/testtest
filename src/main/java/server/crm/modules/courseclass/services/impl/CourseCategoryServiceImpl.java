package server.crm.modules.courseclass.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.converters.DtoToCourseCategory;
import server.crm.core.model.CourseCategoryDto;
import server.crm.core.model.LoggedUser;
import server.crm.core.model.UpdateCategoryResponse;
import server.crm.entities.CourseCategory;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.courseclass.repositories.CourseCategoryRepository;
import server.crm.modules.courseclass.services.CourseCategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private DtoToCourseCategory converter;

    @Override
    public List<CourseCategory> findAllCourseCategory() {
        return courseCategoryRepository.findAllByStatus(true);
    }

    @Override
    public Page<CourseCategory> findAllCourseCategoryPaginated(int page, int size, Sort sortBy) {
        return courseCategoryRepository.findAllByStatus(PageRequest.of(page, size, sortBy), true);
    }

    @Override
    public CourseCategory createCourseCategory(CourseCategory courseCategory) {
        courseCategory.getChildren().forEach(child -> child.setParent(null));
        courseCategory.setCreatedBy(LoggedUser.get());
        courseCategory.setCreatedDate(new Date());
        CourseCategory savedCategory = courseCategoryRepository.save(courseCategory);
        courseCategory.getChildren().forEach(child -> {
            child = courseCategoryRepository.findById(child.getId()).get();
            child.setParent(courseCategory);
            child.setUpdatedBy(LoggedUser.get());
            child.setUpdatedDate(new Date());
            courseCategoryRepository.save(child);
        });
        return savedCategory;
    }

    @Override
    public CourseCategory updateCourseCategory(CourseCategory courseCategory) {
        try {
            Optional<CourseCategory> target = courseCategoryRepository.findById(courseCategory.getId());
            if (target.isPresent()) {
                courseCategory.setCreatedDate(target.get().getCreatedDate());
                courseCategory.setCreatedBy(target.get().getCreatedBy());
                courseCategory.setUpdatedBy(LoggedUser.get());
                courseCategory.setUpdatedDate(new Date());
                courseCategory.getChildren().forEach(child -> child.setParent(null));
                CourseCategory savedCategory = courseCategoryRepository.save(courseCategory);
                courseCategory.getChildren().forEach(child -> {
                    child = courseCategoryRepository.findById(child.getId()).get();
                    child.setParent(courseCategory);
                    child.setUpdatedBy(LoggedUser.get());
                    child.setUpdatedDate(new Date());
                    courseCategoryRepository.save(child);
                });
                return savedCategory;
            }
            throw new Exception();
        } catch (Exception e) {
            throw new ApiRuntimeException("OHHHH");
        }
    }

    @Override
    public CourseCategory deleteCourseCategory(String id) {
        try {
            Optional<CourseCategory> target = courseCategoryRepository.findById(Long.valueOf(id));
            if (target.isPresent()) {
                target.get().setStatus(false);
                target.get().setUpdatedBy(LoggedUser.get());
                target.get().setUpdatedDate(new Date());
                return courseCategoryRepository.save(target.get());
            } else {
                throw new ApiRuntimeException("Can't find class");
            }
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public Page<CourseCategory> findAllCourseCategoryByKeyword(String keyword, int page, int size, Sort sortBy) {
        return courseCategoryRepository.findByNameContainingIgnoreCaseAndStatusIsTrue(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public CourseCategory findCourseCategoryById(String id) {
        try {
            return courseCategoryRepository.findById(Long.valueOf(id)).get();
        } catch (NumberFormatException e) {
            throw new ApiRuntimeException("Wrong ID number!");
        } catch (Exception e) {
            throw new ApiRuntimeException("Something went wrong!");
        }
    }

    // TODO: Refactor code here
    @Override
    public UpdateCategoryResponse genChildrenAndParent(CourseCategoryDto courseCategory, boolean isUpdateParent) {

        CourseCategory target = converter.convert(courseCategory);
        try {
            target.getChildren().forEach(child -> child.setParent(target));
            target.getParent().getChildren().add(target);
        } catch (NullPointerException e) {
            //Nothing here
        }

        if (isUpdateParent && target.getParent() != null) {
            target.getChildren().removeIf(child -> child.getId() == target.getParent().getId());
        } else {
            try {
                if (target.getChildren().stream().filter(cat -> cat.getId() == target.getParent().getId()).toArray().length > 0) {
                    target.setParent(null);
                }
            } catch (NullPointerException e) {

            }
        }
        List<CourseCategory> allCategories = this.findAllCourseCategory();
        List<CourseCategory> parent = new ArrayList<>();
        parent.addAll(allCategories.stream().filter(cat -> checkIfCanParentOf(cat, target)).distinct().collect(Collectors.toList()));
        List<CourseCategory> children = new ArrayList<>();
        children.addAll(allCategories.stream().filter(cat -> checkIfCanChildOf(cat, target)).distinct().collect(Collectors.toList()));
        target.getChildren().removeIf(child -> children.stream().filter(c -> c.getId() == child.getId()).toArray().length <= 0);
        try {
            if (parent.stream().filter(cat -> cat.getId() == target.getParent().getId()).toArray().length <= 0) {
                target.setParent(null);
            }
        } catch (NullPointerException e) {

        }

        target.getChildren().forEach(child -> child.setParent(null));
        UpdateCategoryResponse response = new UpdateCategoryResponse();
        response.setCurrentCourseCategory(target);
        response.setListChildrenCandidate(children);
        response.setListParentCandidate(parent);

        return response;
    }

    private List<CourseCategory> getFullChildren(CourseCategory courseCategory) {
        if (courseCategory == null ||
                courseCategory.getChildren() == null ||
                courseCategory.getChildren().size() <= 0) return new ArrayList<>();
        if (courseCategory.getId() > 0) {
            courseCategory = this.findCourseCategoryById(String.valueOf(courseCategory.getId()));
        }
        List<CourseCategory> listChildren = new ArrayList<>();

        courseCategory.getChildren().forEach(cat -> {
            listChildren.add(cat);
            this.getFullChildren(cat).forEach(cat1 -> listChildren.add(cat1));
        });
        return listChildren;
    }

    //Check if candidate can be a parent of target
    /*
        Điều kiện để một category có thể làm cha của category truyền vào:
         - Nó không phải là con hoặc cháu chắt của category truyền vào. Phải viết hàm getFullChildren
         -
    */
    private boolean checkIfCanParentOf(CourseCategory candidate, CourseCategory target) {
        List<CourseCategory> children = this.getFullChildren(target);
        //If target's children have one child that has id equals candidate, candidate can't be a parent of target
        return children.stream().filter(cat -> cat.getId() == candidate.getId()).toArray().length == 0;
    }

    /*
    Điều kiện để một category có thể làm con của category truyền vào:
     - Nó chưa có cha
     - Category truyền vào không phải là con cháu của nó
    */
    private boolean checkIfCanChildOf(CourseCategory candidate, CourseCategory target) {
        if (candidate.getParent() != null && candidate.getParent().getId() > 0) return false;
        List<CourseCategory> candidateChildren = this.getFullChildren(candidate);
        return candidateChildren.stream().filter(cat -> cat.getId() == target.getId()).toArray().length == 0;
    }
}
