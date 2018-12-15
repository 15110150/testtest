package server.crm.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "invoices")
public class Invoice extends BaseEntity{
    private String code;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    private long discountValue;
    private String note;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Class aclass;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    private CourseDiscount courseDiscount;

    public Invoice(){}

    public Invoice(String code, Student student, long discountValue, String note, String content, Class aclass, Employee employee, CourseDiscount courseDiscount) {
        this.code = code;
        this.student = student;
        this.discountValue = discountValue;
        this.note = note;
        this.content = content;
        this.aclass = aclass;
        this.employee = employee;
        this.courseDiscount = courseDiscount;
    }

    public Invoice(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String code, Student student, long discountValue, String note, String content, Class aclass, Employee employee, CourseDiscount courseDiscount) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.code = code;
        this.student = student;
        this.discountValue = discountValue;
        this.note = note;
        this.content = content;
        this.aclass = aclass;
        this.employee = employee;
        this.courseDiscount = courseDiscount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(long discountValue) {
        this.discountValue = discountValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getAclass() {
        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CourseDiscount getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(CourseDiscount courseDiscount) {
        this.courseDiscount = courseDiscount;
    }
}
