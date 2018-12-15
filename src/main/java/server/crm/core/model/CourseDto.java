package server.crm.core.model;

import server.crm.entities.*;
import server.crm.entities.Class;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author: khoa1
 * @create: 10/11/2018
 */
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    @Lob
    private String content;
    private boolean longTermCourse;
    private Long courseCategory;
    private Set<Class> classes;
    private Set<CourseDiscount> courseDiscounts;
    private Set<MarketingCampaign> marketingCampaign;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLongTermCourse() {
        return longTermCourse;
    }

    public void setLongTermCourse(boolean longTermCourse) {
        this.longTermCourse = longTermCourse;
    }

    public Long getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(Long courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public Set<CourseDiscount> getCourseDiscounts() {
        return courseDiscounts;
    }

    public void setCourseDiscounts(Set<CourseDiscount> courseDiscounts) {
        this.courseDiscounts = courseDiscounts;
    }

    public Set<MarketingCampaign> getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(Set<MarketingCampaign> marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Course convertCourse(){
        Course course = new Course();
        course.setId(this.id);
        course.setName(this.name);
        course.setDescription(this.description);
        course.setContent(this.content);
        course.setClasses(this.classes);
        course.setCourseDiscounts(this.courseDiscounts);
        course.setLongTermCourse(this.longTermCourse);
        course.setMarketingCampaign(this.marketingCampaign);
        course.setUpdatedBy(this.updatedBy);
        course.setCreatedBy(this.createdBy);
        course.setCreatedDate(this.createdDate);
        course.setUpdatedDate(this.updatedDate);
        CourseCategory courseCategory = new CourseCategory();
        courseCategory.setId(this.courseCategory);
        course.setCourseCategory(courseCategory);
        return course;
    }
}
