package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "potential_students")
public class PotentialStudent extends BaseEntity {
    private String name;
    @Column( columnDefinition="DATETIME")
    private Date birthDay;
    @ManyToOne(fetch = FetchType.LAZY)
    private Job job;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    public PotentialStudent(){}

    public PotentialStudent(String name, Date birthDay, Job job, Contact contact) {
        this.name = name;
        this.birthDay = birthDay;
        this.job = job;
        this.contact = contact;
    }

    public PotentialStudent(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, Date birthDay, Job job, Contact contact) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.birthDay = birthDay;
        this.job = job;
        this.contact = contact;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
