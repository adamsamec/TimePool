package cz.timepool.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Comment extends TemporalEntity {

    @ManyToOne
    private User author;

    @ManyToOne
    private Event event;

    @Column(nullable = false)
    private String text;

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addAuthoredComment(this);
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
        event.addComment(this);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
