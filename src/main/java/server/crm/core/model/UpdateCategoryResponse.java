package server.crm.core.model;

import server.crm.entities.Course;
import server.crm.entities.CourseCategory;

import java.util.List;

/**
 * @author: khoa1
 * @create: 05/11/2018
 */
public class UpdateCategoryResponse {
    private List<CourseCategory> listChildrenCandidate;
    private List<CourseCategory> listParentCandidate;
    private CourseCategory currentCourseCategory;

    public List<CourseCategory> getListChildrenCandidate() {
        return listChildrenCandidate;
    }

    public void setListChildrenCandidate(List<CourseCategory> listChildrenCandidate) {
        this.listChildrenCandidate = listChildrenCandidate;
    }

    public List<CourseCategory> getListParentCandidate() {
        return listParentCandidate;
    }

    public void setListParentCandidate(List<CourseCategory> listParentCandidate) {
        this.listParentCandidate = listParentCandidate;
    }

    public CourseCategory getCurrentCourseCategory() {
        return currentCourseCategory;
    }

    public void setCurrentCourseCategory(CourseCategory currentCourseCategory) {
        this.currentCourseCategory = currentCourseCategory;
    }
}
