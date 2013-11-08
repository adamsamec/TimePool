
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Event extends AbstractBusinessObject{
    @ManyToOne
    private User author;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String location;
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @OneToMany(cascade= CascadeType.REMOVE, mappedBy="event")
    private List<Tag> tags;
    
    @OneToMany(cascade= CascadeType.REMOVE, mappedBy="event")
    private List<Term> terms;
    
    public void addTerm (Term term){
        if(this.terms == null){
            this.terms = new ArrayList<Term>();
        }
        if(!this.terms.contains(term)){
            this.terms.add(term);
        }
    }
    
    public void addTag(Tag tag){
        if(this.tags == null){
            this.tags = new ArrayList<Tag>();
        }
        if(!this.tags.contains(tag)){
            this.tags.add(tag);
        }
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addAuthoredEvent(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }
    
    
}
