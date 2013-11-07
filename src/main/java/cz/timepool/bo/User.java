
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
@Table(name="users")
public class User extends AbstractBusinessObject{
    @Column (nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String surname;
    
    @Column(nullable=false)
    private String password;
    
    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;
    
    @OneToMany
    private List<Event> authoredEvents;
    
    @OneToMany
    private List<Term> authoredTerms;
    
    
    public void addAuthoredEvent(Event event){
        if(authoredEvents ==null){
            authoredEvents = new ArrayList<Event>();
        }
        if(!authoredEvents.contains(event)){
            authoredEvents.add(event);
        }
    }
    
    public void addAuthoredTerms(Term term){
        if(authoredTerms ==null){
            authoredTerms = new ArrayList<Term>();
        }
        if(!authoredTerms.contains(term)){
            authoredTerms.add(term);
        }
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
    
    
    
}
