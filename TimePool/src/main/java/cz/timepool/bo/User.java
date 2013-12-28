package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Lukas Lowinger
 */
@NamedQueries({
    @NamedQuery(name = "findUsersById", query = "from User u where u.id= :idUser")})
@Entity
@Table(name = "users")
public class User extends AbstractBusinessObject {

    @Column(unique = true)
    private String email;

    //@Column(nullable = false)
    private String name;

    //@Column(nullable = false)
    private String surname;

    //@Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Integer authKey;

    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private List<Event> authoredEvents;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private List<Term> authoredTerms;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy(clause = "creationDate")
    private List<Comment> authoredComments;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "acceptors_terms",
            joinColumns
            = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns
            = @JoinColumn(name = "term_id", referencedColumnName = "id"))
    private List<Term> acceptedTerms;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "invitedUser")
    private List<EventInvitation> eventInvitations;

    public void addAcceptedTerm(Term term) {
        if (this.acceptedTerms == null) {
            this.acceptedTerms = new ArrayList<Term>();
        }
        if (!this.acceptedTerms.contains(term)) {
            this.acceptedTerms.add(term);
        }
    }

    public void addAuthoredComment(Comment comment) {
        if (this.authoredComments == null) {
            this.authoredComments = new ArrayList<Comment>();
        }
        if (!this.authoredComments.contains(comment)) {
            this.authoredComments.add(comment);
        }
    }

    public void addAuthoredEvent(Event event) {
        if (this.authoredEvents == null) {
            this.authoredEvents = new ArrayList<Event>();
        }
        if (!this.authoredEvents.contains(event)) {
            this.authoredEvents.add(event);
        }
    }

    public void addAuthoredTerms(Term term) {
        if (this.authoredTerms == null) {
            this.authoredTerms = new ArrayList<Term>();
        }
        if (!this.authoredTerms.contains(term)) {
            this.authoredTerms.add(term);
        }
    }

    public List<Comment> getAuthoredComments() {
        return this.authoredComments;
    }

    public void setAuthoredComments(List<Comment> authoredComments) {
        this.authoredComments = authoredComments;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Event> getAuthoredEvents() {
        if (this.authoredEvents == null) {
            this.authoredEvents = new ArrayList<Event>();
        }
        return this.authoredEvents;
    }

    public void setAuthoredEvents(List<Event> authoredEvents) {
        this.authoredEvents = authoredEvents;
    }

    public List<Term> getAuthoredTerms() {
        if (this.authoredTerms == null) {
            this.authoredTerms = new ArrayList<Term>();
        }
        return this.authoredTerms;
    }

    public void setAuthoredTerms(List<Term> authoredTerms) {
        this.authoredTerms = authoredTerms;
    }

    public List<Term> getAcceptedTerms() {
        if (this.acceptedTerms == null) {
            this.acceptedTerms = new ArrayList<Term>();
        }
        return this.acceptedTerms;
    }

    public void setAcceptedTerms(List<Term> participedTerms) {
        this.acceptedTerms = participedTerms;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getAuthKey() {
        return authKey;
    }

    public void setAuthKey(Integer authKey) {
        this.authKey = authKey;
    }

    public List<EventInvitation> getEventInvitations() {
        return eventInvitations;
    }

    public void setEventInvitations(List<EventInvitation> eventInvitations) {
        this.eventInvitations = eventInvitations;
    }

}
