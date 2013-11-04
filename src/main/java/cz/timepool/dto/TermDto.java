
package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
class TermDto extends AbstractDto{
    private Date date;
    private String status;
    private String description;
    private List<Long> comments;
    private Date creationDate;
    private Long author;
    private List<Long> participants;

    public TermDto(Long id, Date date, String status, String description, List<Long> comments, Date creationDate, Long author, List<Long> participants) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.description = description;
        this.comments = comments;
        this.creationDate = creationDate;
        this.author = author;
        this.participants = participants;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }
    
    
}
