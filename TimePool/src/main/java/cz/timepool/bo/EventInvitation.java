package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas L.
 */
@Entity
public class EventInvitation extends TemporalEntity {

    @ElementCollection(targetClass = UserPermission.class)
    private List<UserPermission> permissions;

    private String message;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date expirationDate;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User invitedUser;

    public void addUserPermission(UserPermission up) {
        if (permissions == null) {
            permissions = new ArrayList<UserPermission>();
        }
        if (!this.permissions.contains(up)) {
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
