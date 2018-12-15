package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "marketing_campaigns")
public class MarketingCampaign extends BaseEntity {
    private String name;
    @Column( columnDefinition="DATETIME")
    private Date startDate;
    @Column( columnDefinition="DATETIME")
    private Date endDate;
    private String campaignType;
    private String frequency;
    private String content;
    private String objective;
    private long expectedRevenue;
    private long actualCost;
    private long expectedCost;
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToMany(mappedBy = "id.marketingCampaign")
    private Set<MarketingCampaignHistory> marketingCampaignHistorys;

    public MarketingCampaign(){}

    public MarketingCampaign(String name, Date startDate, Date endDate, String campaignType, String frequency, String content, String objective, long expectedRevenue, long actualCost, long expectedCost, Employee employee, Course course, Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignType = campaignType;
        this.frequency = frequency;
        this.content = content;
        this.objective = objective;
        this.expectedRevenue = expectedRevenue;
        this.actualCost = actualCost;
        this.expectedCost = expectedCost;
        this.employee = employee;
        this.course = course;
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }

    public MarketingCampaign(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, Date startDate, Date endDate, String campaignType, String frequency, String content, String objective, long expectedRevenue, long actualCost, long expectedCost, Employee employee, Course course, Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignType = campaignType;
        this.frequency = frequency;
        this.content = content;
        this.objective = objective;
        this.expectedRevenue = expectedRevenue;
        this.actualCost = actualCost;
        this.expectedCost = expectedCost;
        this.employee = employee;
        this.course = course;
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public long getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(long expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public long getActualCost() {
        return actualCost;
    }

    public void setActualCost(long actualCost) {
        this.actualCost = actualCost;
    }

    public long getExpectedCost() {
        return expectedCost;
    }

    public void setExpectedCost(long expectedCost) {
        this.expectedCost = expectedCost;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<MarketingCampaignHistory> getMarketingCampaignHistorys() {
        return marketingCampaignHistorys;
    }

    public void setMarketingCampaignHistorys(Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }
}
