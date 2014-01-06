package cz.timepool.dto;

import java.util.Date;

/**
 *
 * @author Lukas Lowinger
 */
public class CommentDto extends TemporalEntityDto {

    private Long author;
    private Long event;
    private String text;

    public CommentDto() {
    }

    public CommentDto(Long id, Long author, Long event, String text, Date creationDate) {
        this.id = id;
        this.author = author;
        this.event = event;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentDto ::: ID: " + id + ", creationDate: " + creationDate + ", text: " + text;
    }

}
