//package server.crm.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import java.util.Date;
//import java.util.Set;
//
//@Entity(name = "sms_campains")
//public class sms_campains extends BaseEntity{
//    private String name;
//    private Date start_date;
//    private Date end_date;
//    private String frequency;
//    private String content;
//    private String objective;
//    private long expected_revenue;
//    private long actual_cost;
//    private long expected_cost;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Employee Employee;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Course Course;
//    @ManyToMany(mappedBy = "sms_campains",fetch = FetchType.EAGER)
//    private Set<Contact> Contact;
//}
//none using