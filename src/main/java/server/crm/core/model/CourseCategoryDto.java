package server.crm.core.model;

import server.crm.entities.CourseCategory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: khoa1
 * @create: 05/11/2018
 */
public class CourseCategoryDto {
    private Long id;
    private String name;
    private String description;
    private Long parent;
    private List<CourseCategory> children = new ArrayList<>();
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public List<CourseCategory> getChildren() {
        return children;
    }

    public void setChildren(List<CourseCategory> children) {
        this.children = children;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
}
