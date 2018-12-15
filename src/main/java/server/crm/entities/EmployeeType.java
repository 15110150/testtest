package server.crm.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity(name = "employee_types")
public class EmployeeType extends BaseEntity
{
    private String typeName;
    private String description;
    @OneToMany(mappedBy = "employeeType")
    private Set<Employee> employees;

    public EmployeeType(){}

    public EmployeeType(String typeName, String description, Set<Employee> employees) {
        this.typeName = typeName;
        this.description = description;
        this.employees = employees;
    }

    public EmployeeType(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String typeName, String description, Set<Employee> employees) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.typeName = typeName;
        this.description = description;
        this.employees = employees;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
