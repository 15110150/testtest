package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

// author : trungntm
// date : 13/10/2018 15h00
// entity class named User, will create table name users, extends from class base
@Entity(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    @Column( columnDefinition="DATETIME")
    private Date lastLoginDate;
    @Column( columnDefinition="DATETIME")
    private Date lastActivityDate;
    private boolean isOnline;
    @OneToOne(fetch = FetchType.LAZY)
    private Lecturer lecturer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, Date lastLoginDate, Date lastActivityDate, boolean isOnline, Lecturer lecturer, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastActivityDate = lastActivityDate;
        this.isOnline = isOnline;
        this.lecturer = lecturer;
        this.roles = roles;
    }

    public User(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String username, String password, Date lastLoginDate, Date lastActivityDate, boolean isOnline, Lecturer lecturer, Set<Role> roles) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.username = username;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastActivityDate = lastActivityDate;
        this.isOnline = isOnline;
        this.lecturer = lecturer;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
