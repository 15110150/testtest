package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "course_discounts")
public class CourseDiscount extends BaseEntity{
    private String description;
    private long discountValue;
    private long maxDiscountValue;
    @Column( columnDefinition="DATETIME")
    private Date validFrom;
    @Column( columnDefinition="DATETIME")
    private Date validUntil;
    private String discountCode;
    @OneToMany(mappedBy = "courseDiscount")
    private Set<Invoice> invoices;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public CourseDiscount(){}

    public CourseDiscount(String description, long discountValue, long maxDiscountValue, Date validFrom, Date validUntil, String discountCode, Set<Invoice> invoices, Course course) {
        this.description = description;
        this.discountValue = discountValue;
        this.maxDiscountValue = maxDiscountValue;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.discountCode = discountCode;
        this.invoices = invoices;
        this.course = course;
    }

    public CourseDiscount(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String description, long discountValue, long maxDiscountValue, Date validFrom, Date validUntil, String discountCode, Set<Invoice> invoices, Course course) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.description = description;
        this.discountValue = discountValue;
        this.maxDiscountValue = maxDiscountValue;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.discountCode = discountCode;
        this.invoices = invoices;
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(long discountValue) {
        this.discountValue = discountValue;
    }

    public long getMaxDiscountValue() {
        return maxDiscountValue;
    }

    public void setMaxDiscountValue(long maxDiscountValue) {
        this.maxDiscountValue = maxDiscountValue;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
