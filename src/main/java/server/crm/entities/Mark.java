package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "marks")
public class Mark extends BaseEntity{
    private float midMark;
    private float finalMark;
    private float total;
    private String scoreWords;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Class aclass;

    public Mark(){}

    public Mark(float midMark, float finalMark, float total, String scoreWords, String content, Student student, Class aclass) {
        this.midMark = midMark;
        this.finalMark = finalMark;
        this.total = total;
        this.scoreWords = scoreWords;
        this.content = content;
        this.student = student;
        this.aclass = aclass;
    }

    public Mark(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, float midMark, float finalMark, float total, String scoreWords, String content, Student student, Class aclass) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.midMark = midMark;
        this.finalMark = finalMark;
        this.total = total;
        this.scoreWords = scoreWords;
        this.content = content;
        this.student = student;
        this.aclass = aclass;
    }

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
}
