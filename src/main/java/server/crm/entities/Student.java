package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "students")
public class Student extends BaseEntity{
    private String name;
    @Column( columnDefinition="DATETIME")
    private Date birthDay;
    private Boolean isPotentialStudent;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
    @OneToMany(mappedBy = "student")
    private Set<Invoice> invoices;
    @OneToMany(mappedBy = "student")
    private Set<Mark> marks;

    public Student(){}

    public Student(String name, Date birthDay, Boolean isPotentialStudent, Contact contact, Job job, Set<Invoice> invoices, Set<Mark> marks) {
        this.name = name;
        this.birthDay = birthDay;
        this.isPotentialStudent = isPotentialStudent;
        this.contact = contact;
        this.job = job;
        this.invoices = invoices;
        this.marks = marks;
    }

    public Student(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, Date birthDay, Boolean isPotentialStudent, Contact contact, Job job, Set<Invoice> invoices, Set<Mark> marks) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.birthDay = birthDay;
        this.isPotentialStudent = isPotentialStudent;
        this.contact = contact;
        this.job = job;
        this.invoices = invoices;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getPotentialStudent() {
        return isPotentialStudent;
    }

    public void setPotentialStudent(Boolean potentialStudent) {
        isPotentialStudent = potentialStudent;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
}
