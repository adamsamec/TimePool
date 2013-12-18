package cz.timepool.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Comment extends AbstractBusinessObject {

	@ManyToOne
	private User author;

	@ManyToOne
	private Event event;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;

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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
