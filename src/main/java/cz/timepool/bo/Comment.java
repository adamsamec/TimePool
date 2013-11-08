
package cz.timepool.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Comment extends AbstractBusinessObject{
    @ManyToOne
    @JoinColumn(name = "userId")
    private User author;
    
    @ManyToOne
    private Term term;
    
    @Column(nullable = false)
    private String text;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationDate;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addAuthoredComment(this);
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
        term.addComment(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    
}
