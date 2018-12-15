//package server.crm.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import java.util.Date;
//import java.util.Set;
//
//@Entity(name = "email_campaigns")
//public class email_campaigns extends BaseEntity {
//    private String name;
//    private String desciption;
//    private Date start_date;
//    private Date end_date;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private email_templates email_templates;
//    @ManyToMany(mappedBy = "email_campaigns",fetch = FetchType.EAGER)
//    private Set<Contact> Contact;
//}
//none using