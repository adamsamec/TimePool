
package cz.timepool.bo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Term extends AbstractBusinessObject{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    private String status;
    
    private String description;
    
    @OneToMany
    private List<Comment> comments;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @ManyToOne
    private User author;
    
    @OneToMany
    private List<User> participants;
    
    @ManyToOne
    private Event event;
}
