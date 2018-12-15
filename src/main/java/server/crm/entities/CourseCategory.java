package server.crm.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "course_category")
public class CourseCategory extends BaseEntity{
    private String name;
    private String description;
    @JoinColumn(name = "parent_cate_id")
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private CourseCategory parent;
    @OneToMany(
            mappedBy = "parent",
            fetch = FetchType.LAZY
    )

    private List<CourseCategory> children;
    @OneToMany(
            mappedBy = "courseCategory"
    )
    private Set<Course> courses;

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

    public CourseCategory getParent() {
        return parent;
    }

    public void setParent(CourseCategory parent) {
        this.parent = parent;
    }

    public List<CourseCategory> getChildren() {
        return children;
    }

    public void setChildren(List<CourseCategory> children) {
        this.children = children;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
