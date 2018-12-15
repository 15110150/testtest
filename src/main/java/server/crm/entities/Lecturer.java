package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "lecturers")
public class Lecturer extends BaseEntity{
    private String name;
    @Column( columnDefinition="DATETIME")
    private Date birthday;
    private String email;
    private String phoneNumber;
    private String identityNo;
    private String passport;
    private String diploma;
    private String major;
    private String description;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "id.lecturer")
    private Set<ClassLecturer> classLecturers;

    public Lecturer(){}

    public Lecturer(String name, Date birthday, String email, String phoneNumber, String identityNo, String passport, String diploma, String major, String description, String address, Image image, User user, Set<ClassLecturer> classLecturers) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.identityNo = identityNo;
        this.passport = passport;
        this.diploma = diploma;
        this.major = major;
        this.description = description;
        this.address = address;
        this.image = image;
        this.user = user;
        this.classLecturers = classLecturers;
    }

    public Lecturer(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, Date birthday, String email, String phoneNumber, String identityNo, String passport, String diploma, String major, String description, String address, Image image, User user, Set<ClassLecturer> classLecturers) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.identityNo = identityNo;
        this.passport = passport;
        this.diploma = diploma;
        this.major = major;
        this.description = description;
        this.address = address;
        this.image = image;
        this.user = user;
        this.classLecturers = classLecturers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ClassLecturer> getClassLecturers() {
        return classLecturers;
    }

    public void setClassLecturers(Set<ClassLecturer> classLecturers) {
        this.classLecturers = classLecturers;
    }
}
