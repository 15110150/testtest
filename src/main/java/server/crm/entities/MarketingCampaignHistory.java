package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "marketing_campaign_historys")
public class MarketingCampaignHistory {
    @EmbeddedId
    private MarketingCampaignHistoryId id;
    private String detail;
    private Boolean isCompelete;
    @Column( columnDefinition="DATETIME")
    private Date createdDate;

    public MarketingCampaignHistory(){}

    public MarketingCampaignHistory(MarketingCampaignHistoryId id, String detail, Boolean isCompelete, Date createdDate) {
        this.id = id;
        this.detail = detail;
        this.isCompelete = isCompelete;
        this.createdDate = createdDate;
    }

    public MarketingCampaignHistoryId getId() {
        return id;
    }

    public void setId(MarketingCampaignHistoryId id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getCompelete() {
        return isCompelete;
    }

    public void setCompelete(Boolean compelete) {
        isCompelete = compelete;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
