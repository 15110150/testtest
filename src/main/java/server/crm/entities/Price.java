package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "prices")
public class Price extends BaseEntity {
    private long cost;
    private long total;
    @Column( columnDefinition="DATETIME")
    private Date expiryDate;
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_price",
            joinColumns = @JoinColumn(name = "price_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id")
    )
    private Set<Course> courses;

    public Price(long cost, long total, Date expiryDate, String content, Set<Course> courses) {
        this.cost = cost;
        this.total = total;
        this.expiryDate = expiryDate;
        this.content = content;
        this.courses = courses;
    }

    public Price(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, long cost, long total, Date expiryDate, String content, Set<Course> courses) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.cost = cost;
        this.total = total;
        this.expiryDate = expiryDate;
        this.content = content;
        this.courses = courses;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
