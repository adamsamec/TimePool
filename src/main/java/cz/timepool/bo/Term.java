
package cz.timepool.bo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
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
    
    private List<Comment> comments;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    private User author;
    
    private List<User> participants;
}
