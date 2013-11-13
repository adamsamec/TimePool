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
public class Term extends AbstractBusinessObject {

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date termDate;

	private String status;

	private String description;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "term")
	private List<Comment> comments = new ArrayList<Comment>();

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;

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

	public void addComment(Comment comment) {
		if (this.comments == null) {
			this.comments = new ArrayList<Comment>();
		}

		if (!this.comments.contains(comment)) {
			this.comments.add(comment);
		}
	}

	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

	public Date getTermDate() {
		return this.termDate;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
