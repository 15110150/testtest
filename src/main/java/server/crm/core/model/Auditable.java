package server.crm.core.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> implements Serializable {
    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedBy
    protected U updatedBy;

    @LastModifiedDate
    protected LocalDateTime updatedDate;

    protected boolean status;


    public Auditable() {
        this.setCreatedDate(LocalDateTime.now());
        this.setUpdatedDate(LocalDateTime.now());
        this.status = true;
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public U getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(U updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
