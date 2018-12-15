package server.crm.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import server.crm.entities.ClassLecturer;
import server.crm.entities.Course;
import server.crm.entities.Invoice;
import server.crm.entities.Mark;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

/**
 * @author: khoa1
 * @create: 13/11/2018
 */
public class ClassDto {
    private Long id;
    private String name;
    private int noStudent;
    private Date openDate;
    private Date endDate;
    private String description;
    private String room;
    private Long course;
    private Set<ClassLecturer> classLecturers;
    private Set<Invoice> invoices;
    private Set<Mark> marks;

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

    public int getNoStudent() {
        return noStudent;
    }

    public void setNoStudent(int noStudent) {
        this.noStudent = noStudent;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Set<ClassLecturer> getClassLecturers() {
        return classLecturers;
    }

    public void setClassLecturers(Set<ClassLecturer> classLecturers) {
        this.classLecturers = classLecturers;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
