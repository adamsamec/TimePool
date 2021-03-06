package cz.timepool.dto;

import cz.timepool.bo.UserRole;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class UserDto extends TemporalEntityDto {

    private String email;
    private String name;
    private String surname;
    private String password;
    private String description;
    private Date creationDate;
    private UserRole userRole;
    private Integer authKey;

    private List<Long> eventInvitations;
    private List<Long> authoredEvents;
    private List<Long> authoredTerms;
    private List<Long> authoredComments;
    private List<Long> acceptedTerms;

    public UserDto(Long id, String email, String name, String surname, String password, String description, UserRole userRole, Integer authKey, Date creationDate, List<Long> authoredEvents, List<Long> authoredTerms, List<Long> authoredComments, List<Long> acceptedTerms, List<Long> eventInvitations) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.description = description;
        this.creationDate = creationDate;
        this.authoredEvents = authoredEvents;
        this.authoredTerms = authoredTerms;
        this.authoredComments = authoredComments;
        this.acceptedTerms = acceptedTerms;
        this.userRole = userRole;
        this.eventInvitations = eventInvitations;
        this.authKey = authKey;
    }

    public Integer getAuthKey() {
        return authKey;
    }

    public void setAuthKey(Integer authKey) {
        this.authKey = authKey;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Long> getEventInvitations() {
        return eventInvitations;
    }

    public void setEventInvitations(List<Long> eventInvitations) {
        this.eventInvitations = eventInvitations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Long> getAuthoredEvents() {
        return authoredEvents;
    }

    public void setAuthoredEvents(List<Long> authoredEvents) {
        this.authoredEvents = authoredEvents;
    }

    public List<Long> getAuthoredTerms() {
        return authoredTerms;
    }

    public void setAuthoredTerms(List<Long> authoredTerms) {
        this.authoredTerms = authoredTerms;
    }

    public List<Long> getAuthoredComments() {
        return authoredComments;
    }

    public void setAuthoredComments(List<Long> authoredComments) {
        this.authoredComments = authoredComments;
    }

    public List<Long> getAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(List<Long> acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }

    @Override
    public String toString() {
        return "UserDto ::: ID: " + id + ", creationDate: " + creationDate + ", name: " + name + ", surname: " + surname + ", email: " + email;
    }

}
