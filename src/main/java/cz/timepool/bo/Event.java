package cz.timepool.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
@NamedQuery(name = "Event.getAllWithTags", query = "SELECT e FROM Event e WHERE e.tags in :tags")
public class Event extends AbstractBusinessObject {

	@ManyToOne
	private User author;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String location;
	private String description;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "events_tags",
			joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "ID"))
	private List<Tag> tags;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "event")
	@OrderBy(clause = "termDate ASC")
	private List<Term> terms;

	@Transient
	private String currentTermsOrder = "termDate ASC";
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "event")
	private List<EventInvitation> invitations;

	public void addTerm(Term term) {
		if (this.terms == null) {
			this.terms = new ArrayList<Term>();
		}
		if (!this.terms.contains(term)) {
			this.terms.add(term);
			this.currentTermsOrder = "UNORDERED";
		}
	}

	public void removeTerm(Term term) {
		this.terms.remove(term);
	}

	public void addTag(Tag tag) {
		if (this.tags == null) {
			this.tags = new ArrayList<Tag>();
		}
		if (!this.tags.contains(tag)) {
			this.tags.add(tag);
		}
	}

	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(User author) {
		this.author = author;
		author.addAuthoredEvent(this);
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Term> getTerms() {
		if (this.terms == null) {
			this.terms = new ArrayList<Term>();
		} else {
			String termsOrder = "termDate ASC";
			if (!this.currentTermsOrder.equals(termsOrder)) {
				Collections.sort(this.terms, new Comparator<Term>() {
					public int compare(Term t1, Term t2) {
						return t1.getTermDate().compareTo(t2.getTermDate());
					}
				});
				this.currentTermsOrder = termsOrder;
			}
		}
		return this.terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

}
