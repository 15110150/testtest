package server.crm.core.model;

import server.crm.entities.*;
import server.crm.entities.Class;

import java.util.Date;


public class MarkDto {
    private Long id;
    private float midMark;
    private float finalMark;
    private float total;
    private String scoreWords;
    private String content;
    private Student student;
    private Class aclass;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private boolean status;

    public float getMidMark() {
        return midMark;
    }

    public void setMidMark(float midMark) {
        this.midMark = midMark;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getScoreWords() {
        return scoreWords;
    }

    public void setScoreWords(String scoreWords) {
        this.scoreWords = scoreWords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class getAclass() {
        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
