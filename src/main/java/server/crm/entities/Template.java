package server.crm.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity(name = "templates")
public class Template extends BaseEntity {
    private String name;
    private String title;
    private String description;
    private String content;;
    @OneToMany(mappedBy = "id.template")
    private Set<MarketingCampaignHistory> marketingCampaignHistorys;

    public Template(){}

    public Template(String name, String title, String description, String content, Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.content = content;
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }

    public Template(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String name, String title, String description, String content, Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.name = name;
        this.title = title;
        this.description = description;
        this.content = content;
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<MarketingCampaignHistory> getMarketingCampaignHistorys() {
        return marketingCampaignHistorys;
    }

    public void setMarketingCampaignHistorys(Set<MarketingCampaignHistory> marketingCampaignHistorys) {
        this.marketingCampaignHistorys = marketingCampaignHistorys;
    }
}