package cz.timepool.dto;

import cz.timepool.bo.UserPermission;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas L.
 */
public class EventInvitationDto extends TemporalEntityDto {

    private List<UserPermission> permissions;
    private String message;
    private Date expirationDate;
    private Long event;
    private Long invitedUser;

    public EventInvitationDto() {
    }

    public EventInvitationDto(Long id, List<UserPermission> permissions, String message, Date expirationDate, Date creationDate, Long event, Long invitedUser) {
        this.id = id;
        this.permissions = permissions;
        this.message = message;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
        this.event = event;
        this.invitedUser = invitedUser;
    }

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

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(Long invitedUser) {
        this.invitedUser = invitedUser;
    }

}
