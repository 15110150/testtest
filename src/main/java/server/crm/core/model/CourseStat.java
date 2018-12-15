package server.crm.core.model;

/**
 * @author: khoa1
 * @create: 27/11/2018
 */
public class CourseStat {
    private Long id;
    private String name;
    private int numberOfClasses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }
}
