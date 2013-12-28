package cz.timepool.pres.bb;

import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserPermission;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.helper.FacesHelper;
import cz.timepool.service.EventsServiceIface;
import cz.timepool.service.UsersServiceIface;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@RequestScoped
@Component
public class EventSingle {

    @Autowired
    private EventsServiceIface eventsService;

    @Autowired
    private UsersServiceIface usersService;

    @Autowired
    private LoginSession loginSession;

    private EventDto event;

    private TermDto term;

    private String commentText;

    private String invitationEmail;

    private String invitationMessage;

    public List<UserPermission> invitationPermissions;

    private static Map<String, Object> permissionOptions;

    static {
        permissionOptions = new LinkedHashMap<String, Object>();
        permissionOptions.put("Can add term", UserPermission.ADD_TERM);
        permissionOptions.put("Can accept term ?", UserPermission.ACCEPT_TERM);
        permissionOptions.put("Can add comment", UserPermission.ADD_COMMENT);
    }

    public List<UserPermission> getInvitationPermissions() {
        return invitationPermissions;
    }

    public void setInvitationPermissions(List<UserPermission> userPermissions) {
        this.invitationPermissions = userPermissions;
    }

    public EventSingle() {
        term = new TermDto(Long.MIN_VALUE, null, StatusEnum.VOLNY, invitationEmail, null, Long.MIN_VALUE, Long.MIN_VALUE, null);
    }

    public Map<String, Object> getPermissionOptions() {
        return permissionOptions;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String comment) {
        this.commentText = comment;
    }

    public String getInvitationEmail() {
        return invitationEmail;
    }

    public void setInvitationEmail(String userEmail) {
        this.invitationEmail = userEmail;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String message) {
        this.invitationMessage = message;
    }

    public EventDto getEvent() {
        if (event == null) {
            event = new EventDto(null, null, null, null, null, null, null, null, null);
        }
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public TermDto getTerm() {
        return term;
    }

    public void setTerm(TermDto term) {
        this.term = term;
    }

    public void setEventById2(Long eventId) {
        event = eventsService.getEventById(eventId);
    }

    public String setEventById(String outcome) {
        Long eventId = Long.valueOf(FacesHelper.getRequestParameter("event_id"));
        event = eventsService.getEventById(eventId);
        return outcome;
    }

    public String addEvent(String outcome) {
        eventsService.addEvent(loginSession.getUser().getId(), event.getTitle(), event.getLocation(), event.getDescription(), new Date());
        return outcome;
    }

    public String editEvent(String outcome) {
        eventsService.editEventDetails(event);
        return outcome;
    }

    public String deleteEvent(String outcome) {
        Long userId = Long.valueOf(FacesHelper.getRequestParameter("edit_event_id"));
        eventsService.deleteEventById(userId);
        return outcome;
    }

    public void inviteToEvent() {
        eventsService.inviteUser(event.getId(), invitationEmail, invitationPermissions, invitationMessage, new Date());
        FacesHelper.addMessage("invitation_email", "User with email: " + invitationEmail + " was sucessfully invited.");
    }

    public List<TermDto> getAllTerms() {
        return eventsService.getTermsByEventId(event.getId());
    }

    public void addTerm() {
        term.setId(eventsService.addTermToEvent(term.getTermDate(), term.getStatus(), term.getDescription(), new Date(), loginSession.getUser().getId(), event.getId()));
    }

    public void addComment() {
        //TODO: term id se musi vybrat rucne a nebo pridat pridavani k tomu termu
        usersService.addCommentToEvent(commentText, loginSession.getUser().getId(), event.getId());
    }

    public List<CommentDto> getAllComments() {
        return eventsService.getAllCommentsByEvent(event.getId());
    }

    public void deleteTerm() {
        Long termId = Long.valueOf(FacesHelper.getRequestParameter("delete_term_id"));
        eventsService.deleteTermById(termId);
    }

    public void deleteComment() {
        Long cmntId = Long.valueOf(FacesHelper.getRequestParameter("delete_comment_id"));
        usersService.deleteCommentById(cmntId);
    }

}
