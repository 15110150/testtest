package server.crm.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "privileges")
public class Privilege extends BaseEntity{
    private String description;
    private String privilegeName;
    @ManyToMany(mappedBy = "privileges",fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Privilege() {
    }

    public Privilege(String description, String privilegeName, Set<Role> roles) {
        this.description = description;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }

    public Privilege(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String description, String privilegeName, Set<Role> roles) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.description = description;
        this.privilegeName = privilegeName;
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
