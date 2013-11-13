package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class TermDto extends TemporalEntityDto {

	private Date termDate;
	private String status;
	private String description;
	private Date creationDate;
	private Long author;
	private Long event;
	private List<Long> acceptors;
	private List<Long> comments;

	public TermDto(Long id, Date termDate, String status, String description, Date creationDate, Long author, Long event, List<Long> acceptors, List<Long> comments) {
		this.id = id;
		this.termDate = termDate;
		this.status = status;
		this.description = description;
		this.creationDate = creationDate;
		this.author = author;
		this.event = event;
		this.acceptors = acceptors;
		this.comments = comments;
	}

	public Long getEvent() {
		return event;
	}

	public void setEvent(Long event) {
		this.event = event;
	}

	public Date getTermDate() {
		return termDate;
	}

	public void setTermDate(Date termDate) {
		this.termDate = termDate;
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

	public List<Long> getComments() {
		return comments;
	}

	public void setComments(List<Long> comments) {
		this.comments = comments;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public List<Long> getAcceptors() {
		return acceptors;
	}

	public void setAcceptors(List<Long> acceptors) {
		this.acceptors = acceptors;
	}

	@Override
	public String toString() {
		return "TermDto ::: ID: " + id + ", creationDate: " + creationDate + ", acceptor ids: " + acceptors + ", status: " + status + ", termDate: " + termDate + ", description: " + description;
	}

}
