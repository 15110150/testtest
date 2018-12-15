package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

// author : trungntm
// date : 13/10/2018 15h00
// BaseEntity is class base for other entities
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column( columnDefinition="DATETIME")
    private Date createdDate;
    private String createdBy;
    @Column( columnDefinition="DATETIME")
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

    public BaseEntity() {
    }

    public BaseEntity(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status) {
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
