
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Term extends AbstractBusinessObject{
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date suggestedDate;
    
    private String status;
    
    private String description;
    
    @OneToMany(cascade= CascadeType.REMOVE , mappedBy="term")
    private List<Comment> comments;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @ManyToOne
    private User author;
    
     @ManyToMany(cascade= CascadeType.REMOVE , mappedBy="participedTerms")
    private List<User> participants;
    
    @ManyToOne
    private Event event;
    
    public void addParticipant(User participant){
        if(this.participants==null){
            this.participants = new ArrayList<User>();
        }
        
        if(!this.participants.contains(participant)){
            participants.add(participant);
        }
    }
    
    public void addComment(Comment comment){
        if(this.comments==null){
            this.comments = new ArrayList<Comment>();
        }
        
        if(!this.comments.contains(comment)){
            comments.add(comment);
        }
    }

    public Date getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(Date suggestedDate) {
        this.suggestedDate = suggestedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addAuthoredTerms(this);
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        event.addTerm(this);
    }
    
        @Override
    public String toString() {
        return "Term ::: ID : "+id+"status : "+status+" description: "+description;
    }
}
