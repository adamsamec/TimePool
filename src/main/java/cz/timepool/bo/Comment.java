
package cz.timepool.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Comment extends AbstractBusinessObject{
    @Column(nullable = false)
    private User author;
    
    @ManyToOne
    private Term term;
    
    @Column(nullable = false)
    private String text;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;


}
