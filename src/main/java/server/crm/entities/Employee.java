package server.crm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "employees")
public class Employee extends BaseEntity{
    private String code;
    private String name;
    private String passPort;
    @Column( columnDefinition="DATETIME")
    private Date birthDay;
    private String indentify;
    private String phoneNumber;
    private String addressHometown;
    private String addressNow;
    private String email;
    private String bankNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeType employeeType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;
    @OneToMany(mappedBy = "employee")
    private Set<Invoice> invoices;
//    @OneToMany(mappedBy = "Employee")
//    private Set<sms_campains> sms_campains;
    @OneToMany(mappedBy = "employee")
    private Set<MarketingCampaign> marketingCampaigns;

    public Employee(){}

    public Employee(String code, String name, String passPort, Date birthDay, String indentify, String phoneNumber, String addressHometown, String addressNow, String email, String bankNo, EmployeeType employeeType, Image image, Set<Invoice> invoices, Set<MarketingCampaign> marketingCampaigns) {
        this.code = code;
        this.name = name;
        this.passPort = passPort;
        this.birthDay = birthDay;
        this.indentify = indentify;
        this.phoneNumber = phoneNumber;
        this.addressHometown = addressHometown;
        this.addressNow = addressNow;
        this.email = email;
        this.bankNo = bankNo;
        this.employeeType = employeeType;
        this.image = image;
        this.invoices = invoices;
        this.marketingCampaigns = marketingCampaigns;
    }

    public Employee(Date createdDate, String createdBy, Date updatedDate, String updatedBy, boolean status, String code, String name, String passPort, Date birthDay, String indentify, String phoneNumber, String addressHometown, String addressNow, String email, String bankNo, EmployeeType employeeType, Image image, Set<Invoice> invoices, Set<MarketingCampaign> marketingCampaigns) {
        super(createdDate, createdBy, updatedDate, updatedBy, status);
        this.code = code;
        this.name = name;
        this.passPort = passPort;
        this.birthDay = birthDay;
        this.indentify = indentify;
        this.phoneNumber = phoneNumber;
        this.addressHometown = addressHometown;
        this.addressNow = addressNow;
        this.email = email;
        this.bankNo = bankNo;
        this.employeeType = employeeType;
        this.image = image;
        this.invoices = invoices;
        this.marketingCampaigns = marketingCampaigns;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassPort() {
        return passPort;
    }

    public void setPassPort(String passPort) {
        this.passPort = passPort;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getIndentify() {
        return indentify;
    }

    public void setIndentify(String indentify) {
        this.indentify = indentify;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressHometown() {
        return addressHometown;
    }

    public void setAddressHometown(String addressHometown) {
        this.addressHometown = addressHometown;
    }

    public String getAddressNow() {
        return addressNow;
    }

    public void setAddressNow(String addressNow) {
        this.addressNow = addressNow;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<MarketingCampaign> getMarketingCampaigns() {
        return marketingCampaigns;
    }

    public void setMarketingCampaigns(Set<MarketingCampaign> marketingCampaigns) {
        this.marketingCampaigns = marketingCampaigns;
    }
}
