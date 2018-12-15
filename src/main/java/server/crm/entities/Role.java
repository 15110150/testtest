package server.crm.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "roles")
public class Role extends BaseEntity{
    private String roleName;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "privileges_roles",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id",referencedColumnName = "id")
    )
    private Set<Privilege> privileges;
    public Role() {
    }

    public Role(String roleName, Set<User> users, Set<Privilege> privileges) {
        this.roleName = roleName;
        this.users = users;
        this.privileges = privileges;
    }

    public Role(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String roleName, Set<User> users, Set<Privilege> privileges) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.roleName = roleName;
        this.users = users;
        this.privileges = privileges;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
