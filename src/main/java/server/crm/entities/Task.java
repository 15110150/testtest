package server.crm.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "tasks")
public class Task extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Contact contact;

    public Task(){}

    public Task(String name, String description, Contact contact) {
        this.name = name;
        this.description = description;
        this.contact = contact;
    }

    public Task(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, String description, Contact contact) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.description = description;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
