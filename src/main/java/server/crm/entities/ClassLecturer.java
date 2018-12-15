package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "class_lecturer")
public class ClassLecturer {
    @EmbeddedId
    private ClassLecturerId id;
    private Boolean isMainLecturer;
    private Date createdDate;
    private String createdBy;
    @Column( columnDefinition="DATETIME")
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

    public ClassLecturer(){}

    public ClassLecturer(ClassLecturerId id, Boolean isMainLecturer, Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status) {
        this.id = id;
        this.isMainLecturer = isMainLecturer;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    public ClassLecturerId getId() {
        return id;
    }

    public void setId(ClassLecturerId id) {
        this.id = id;
    }

    public Boolean getMainLecturer() {
        return isMainLecturer;
    }

    public void setMainLecturer(Boolean mainLecturer) {
        isMainLecturer = mainLecturer;
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
