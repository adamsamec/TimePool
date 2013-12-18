package cz.timepool.bb;

import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserPermission;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.helper.FacesUtil;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
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
@Component
@RequestScoped
public class EventSingle {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginSession loginSession;

    private EventDto event;

    private TermDto term;

    private String commentText;

    private String userEmail;

    private String message;

    private static Map<String, Object> permissionsValues;

    static {
        permissionsValues = new LinkedHashMap<String, Object>();
        permissionsValues.put("Can add term", UserPermission.ADD_TERM); //label, value
        permissionsValues.put("Can accept term ?", UserPermission.ACCEPT_TERM);
        permissionsValues.put("Can add comment", UserPermission.ADD_COMMENT);
    }

    public List<UserPermission> ups;

    public List<UserPermission> getUps() {
        return ups;
    }

    public void setUps(List<UserPermission> ups) {
        this.ups = ups;
    }

    public EventSingle() {
        term = new TermDto(Long.MIN_VALUE, null, StatusEnum.VOLNY, userEmail, null, Long.MIN_VALUE, Long.MIN_VALUE, null);
    }

    public Map<String, Object> getPermissionsValues() {
        return permissionsValues;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String comment) {
        this.commentText = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        Long eventId = Long.valueOf(FacesUtil.getRequestParameter("event_id"));
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
        Long userId = Long.valueOf(FacesUtil.getRequestParameter("edit_event_id"));
        eventsService.deleteEventById(userId);
        return outcome;
    }

    public void inviteToEvent() {
        System.out.println("emai " + getUserEmail() + "message" + getMessage());
        eventsService.inviteUser(event.getId(), userEmail, ups, message, new Date());
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
        Long termId = Long.valueOf(FacesUtil.getRequestParameter("delete_term_tid"));
        System.out.println("mazu term " + termId);
        eventsService.deleteTermById(termId);
    }

    public void deleteComment() {
        Long cmntId = Long.valueOf(FacesUtil.getRequestParameter("delete_comment_id"));
        System.out.println("mazu comment : " + cmntId);
        usersService.deleteCommentById(cmntId);
    }

}
