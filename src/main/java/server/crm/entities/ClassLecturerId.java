package server.crm.entities;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ClassLecturerId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Class aclass;
}
