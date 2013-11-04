
package cz.timepool.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class User extends AbstractBusinessObject{
    private String email;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String surname;
    
    @Column(nullable=false)
    private String password;
    
    private String description;
    
    private String settings;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @ManyToOne
    private Event event;
    
    @OneToMany
    private Term term;
}
