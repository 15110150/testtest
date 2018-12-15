package server.crm.entities;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Embeddable
public class MarketingCampaignHistoryId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketingCampaign marketingCampaign;
    @ManyToOne(fetch = FetchType.LAZY)
    private Template template;
}
