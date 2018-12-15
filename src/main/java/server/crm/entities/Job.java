package server.crm.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;
@Entity(name = "jobs")
public class Job extends BaseEntity{
    private String name;
    private String description;
    @OneToMany(mappedBy = "job")
    private Set<Student> students;
    @OneToMany(mappedBy = "job")
    private Set<PotentialStudent> potentialStudents;

    public Job(){}

    public Job(String name, String description, Set<Student> students, Set<PotentialStudent> potentialStudents) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.potentialStudents = potentialStudents;
    }

    public Job(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, String description, Set<Student> students, Set<PotentialStudent> potentialStudents) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.description = description;
        this.students = students;
        this.potentialStudents = potentialStudents;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<PotentialStudent> getPotentialStudents() {
        return potentialStudents;
    }

    public void setPotentialStudents(Set<PotentialStudent> potentialStudents) {
        this.potentialStudents = potentialStudents;
    }
}
