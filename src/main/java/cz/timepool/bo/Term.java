
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Term extends AbstractBusinessObject{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    private Enum status;
    
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
    
    public void addParticipant(User participant){
        if(this.participants==null){
            this.participants = new ArrayList<User>();
        }
        
        if(!this.participants.contains(participant)){
            participants.add(participant);
        }
    }
}
