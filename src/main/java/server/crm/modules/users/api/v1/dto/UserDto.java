package server.crm.modules.users.api.v1.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : trungntm
 * @date : 23/10/2018 23h20
 * @content : add class UserDto, define type User will be convert to UserDto in ServiceImpl
 */
public class UserDto implements Serializable {
    private long id;
    private String username;
    private String password;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

    public UserDto() {
    }

    public UserDto(long id, String username, String password, Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
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
