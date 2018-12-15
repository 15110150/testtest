package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "contacts")
public class Contact extends BaseEntity {
    private String contactName;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String facebook;
    private String fax;
    private String website;
    private String backAccount;
    private String address1;
    private String address2;
    private String address3;
    @OneToMany(mappedBy = "contact")
    private Set<Note> notes;
    @OneToMany(mappedBy = "contact")
    private Set<Task> tasks;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Student students;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private PotentialStudent potentialStudents;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "emailcam_contacts",
//            joinColumns = @JoinColumn(name = "contact_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "cam_id",referencedColumnName = "id")
//    )
//    private Set<email_campaigns> email_campaigns;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "sms_campaign_contact",
//            joinColumns = @JoinColumn(name = "contact_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "cam_id",referencedColumnName = "id")
//    )
//    private Set<sms_campains> sms_campains;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "campaign_contact",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    )
    private Set<MarketingCampaign> marketingCampaign;


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    public PotentialStudent getPotentialStudents() {
        return potentialStudents;
    }

    public void setPotentialStudents(PotentialStudent potentialStudents) {
        this.potentialStudents = potentialStudents;
    }

    public Set<MarketingCampaign> getMarketingCampaign() {
        return marketingCampaign;
    }

    public void setMarketingCampaign(Set<MarketingCampaign> marketingCampaign) {
        this.marketingCampaign = marketingCampaign;
    }
}
