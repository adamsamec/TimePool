package cz.timepool.dto;

import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class TagDto extends AbstractDto {

    private String text;
    private List<Long> events;

    public TagDto(Long id, List<Long> events, String text) {
	this.id = id;
	this.events = events;
	this.text = text;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public List<Long> getEvents() {
	return events;
    }

    public void setEvents(List<Long> events) {
	this.events = events;
    }

    @Override
    public String toString() {
	return "TagDto ::: ID: " + id + ", text: " + text;
    }
}
