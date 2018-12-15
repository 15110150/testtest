package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "classes")
public class Class extends BaseEntity {
    private String name;
    private int maxStudent;
    private int noStudent;
    private Date openDate;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy HH:mm:ss")
    private Date endDate;
    private String description;
    private String room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Course course;
    @OneToMany(mappedBy = "id.aclass")
    private Set<ClassLecturer> classLecturers;
    @OneToMany(mappedBy = "aclass")
    private Set<Invoice> invoices;
    @OneToMany(mappedBy = "aclass")
    private Set<Mark> marks;

    public Class(){
        this.noStudent = 0;
        this.maxStudent = 0;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }
}
