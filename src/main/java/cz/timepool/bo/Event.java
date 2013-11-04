
package cz.timepool.bo;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Event extends AbstractBusinessObject{
    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String location;
    private String description;
    private String settings;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @OneToMany
    private List<Tag> tags;
    
    @OneToMany
    private List<User> users;
    
    @OneToMany
    private List<Term> terms;
}
