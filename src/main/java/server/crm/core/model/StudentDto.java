package server.crm.core.model;

import server.crm.entities.Contact;
import server.crm.entities.Invoice;
import server.crm.entities.Job;
import server.crm.entities.Mark;

import java.util.Date;
import java.util.Set;

public class StudentDto {
    private Long id;
    private String name;
    private Date birthDay;
    private Boolean isPotentialStudent;
    private Contact contact;
    private Job job;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
