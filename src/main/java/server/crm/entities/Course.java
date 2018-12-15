package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "courses")
public class Course extends BaseEntity{
    private String name;
    private String description;
    @Lob
    private String content;
    private boolean longTermCourse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_category_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("courseCategory")
    private CourseCategory courseCategory;
    @OneToMany(mappedBy = "course")
    private Set<Class> classes;
    @OneToMany(mappedBy = "course")
    private Set<CourseDiscount> courseDiscounts;
//    @OneToMany(mappedBy = "Course")
//    private Set<sms_campains> sms_campains;
    @OneToMany(mappedBy = "course")
    private Set<MarketingCampaign> marketingCampaign;

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

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
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

    public boolean isLongTermCourse() {
        return longTermCourse;
    }

    public void setLongTermCourse(boolean longTermCourse) {
        this.longTermCourse = longTermCourse;
    }
}
