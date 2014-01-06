package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Term extends TemporalEntity {

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date termDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private String description;

    @ManyToOne
    private User author;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "acceptedTerms")
    private List<User> acceptors;

    @ManyToOne
    private Event event;

    public void addAcceptor(User acceptor) {
        if (this.acceptors == null) {
            this.acceptors = new ArrayList<User>();
        }

        if (!this.acceptors.contains(acceptor)) {
            this.acceptors.add(acceptor);
        }
    }

    public Date getTermDate() {
        return this.termDate;
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addAuthoredTerms(this);
    }

    public List<User> getAcceptors() {
        if (this.acceptors == null) {
            this.acceptors = new ArrayList<User>();
        }
        return this.acceptors;
    }

    public void setAcceptors(List<User> acceptors) {
        this.acceptors = acceptors;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
        event.addTerm(this);
    }

}
