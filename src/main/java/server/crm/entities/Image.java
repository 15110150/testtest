package server.crm.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "image")
public class Image extends BaseEntity{
    private String fileName;
    private String imgPath;
    private String filePath;

    @OneToMany(mappedBy = "image")
    private Set<Lecturer> lecturers;

    @OneToMany(mappedBy = "image")
    private Set<Employee> employees;

    public Image() {
    }

    public Image(String fileName, String imgPath, String filePath, Set<Lecturer> lecturers, Set<Employee> employees) {
        this.fileName = fileName;
        this.imgPath = imgPath;
        this.filePath = filePath;
        this.lecturers = lecturers;
        this.employees = employees;
    }

    public Image(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String fileName, String imgPath, String filePath, Set<Lecturer> lecturers, Set<Employee> employees) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.fileName = fileName;
        this.imgPath = imgPath;
        this.filePath = filePath;
        this.lecturers = lecturers;
        this.employees = employees;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
