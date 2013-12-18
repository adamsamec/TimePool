
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas L.
 */
@Entity
public class EventInvitation extends AbstractBusinessObject{
    @ElementCollection(targetClass=UserPermission.class)
    private List<UserPermission> permissions;
    
    private String message;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date expirationDate;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @ManyToOne
    private Event event;
    
    @ManyToOne
    private User invitedUser;
    
    public void addUserPermission(UserPermission up){
	if(permissions == null){
	    permissions = new ArrayList<UserPermission>();
	}
	if(!this.permissions.contains(up)){
	    this.permissions.add(up);
	}
    }

    public List<UserPermission> getPermissions() {
	return permissions;
    }

    public void setPermissions(List<UserPermission> permissions) {
	this.permissions = permissions;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Date getExpirationDate() {
	return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public Event getEvent() {
	return event;
    }

    public void setEvent(Event event) {
	this.event = event;
    }

    public User getInvitedUser() {
	return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
	this.invitedUser = invitedUser;
    }
    
    
}
