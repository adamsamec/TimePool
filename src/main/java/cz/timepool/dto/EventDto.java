package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class EventDto extends TemporalEntityDto {

	private Long author;
	private String title;
	private String location;
	private String description;
	private Date creationDate;
	private List<Long> tags;
	private List<Long> terms;

	public EventDto(Long id, Long author, String title, String location, String description, Date creationDate, List<Long> tags, List<Long> terms) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.location = location;
		this.description = description;
		this.creationDate = creationDate;
		this.tags = tags;
		this.terms = terms;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
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

	public List<Long> getTags() {
		return tags;
	}

	public void setTags(List<Long> tags) {
		this.tags = tags;
	}

	public List<Long> getTerms() {
		return terms;
	}

	public void setTerms(List<Long> terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "EventDto ::: ID: " + id + ", creationDate: " + creationDate + ", title: " + title + ", description: " + description;
	}

}
