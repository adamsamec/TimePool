package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({ @NamedQuery(name = "findUsersById", query = "from User u where u.id= :idUser" )})//netusim kde to pouzit
@Entity
@Table(name = "users")
public class User extends AbstractBusinessObject {

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String surname;
    
    @Column(nullable = false)
    private String password;
    
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private List<Event> authoredEvents;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    private List<Term> authoredTerms;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy(clause="creationDate")
    private List<Comment> authoredComments;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "users_participedTerm",
    joinColumns = @JoinColumn(name = "participed_users_id", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "term_id", referencedColumnName = "ID"))
    private List<Term> participedTerms;

    public void addParticipedTerm(Term term) {
        if (this.participedTerms == null) {
            this.participedTerms = new ArrayList<Term>();
        }

        if (!this.participedTerms.contains(term)) {
            this.participedTerms.add(term);
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
        if (authoredEvents == null) {
            authoredEvents = new ArrayList<Event>();
        }
        if (!authoredEvents.contains(event)) {
            authoredEvents.add(event);
        }
    }

    public void addAuthoredTerms(Term term) {
        if (authoredTerms == null) {
            authoredTerms = new ArrayList<Term>();
        }
        if (!authoredTerms.contains(term)) {
            authoredTerms.add(term);
        }
    }

    public List<Comment> getAuthoredComments() {
        return authoredComments;
    }

    public void setAuthoredComments(List<Comment> authoredComments) {
        this.authoredComments = authoredComments;
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

    public List<Event> getAuthoredEvents() {
        return authoredEvents;
    }

    public void setAuthoredEvents(List<Event> authoredEvents) {
        this.authoredEvents = authoredEvents;
    }

    public List<Term> getAuthoredTerms() {
        return authoredTerms;
    }

    public void setAuthoredTerms(List<Term> authoredTerms) {
        this.authoredTerms = authoredTerms;
    }

    public List<Term> getParticipedTerms() {
        return participedTerms;
    }

    public void setParticipedTerms(List<Term> participedTerms) {
        this.participedTerms = participedTerms;
    }
}
