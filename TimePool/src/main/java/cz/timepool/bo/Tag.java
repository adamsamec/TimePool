package cz.timepool.bo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Tag extends AbstractBusinessObject {

	@Column(nullable = false)
	private String text;

	@ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "tags")
	private List<Event> events;

	public void addEvent(Event e) {
		if (this.events == null) {
			this.events = new ArrayList<Event>();
		}
		if (!this.events.contains(e)) {
			this.events.add(e);
		}
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Event> getEvents() {
		if (this.events == null) {
			this.events = new ArrayList<Event>();
		}
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
