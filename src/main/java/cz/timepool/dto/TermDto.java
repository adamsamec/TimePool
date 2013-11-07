
package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class TermDto extends AbstractDto{
    private Date suggestedDate;
    private String status;
    private String description;
    private Date creationDate;
    private Long author;
    private List<Long> participants;
    private List<Long> comments;

    public TermDto(Date suggestedDate, String status, String description, Date creationDate, Long author, List<Long> participants, List<Long> comments) {
        this.suggestedDate = suggestedDate;
        this.status = status;
        this.description = description;
        this.creationDate = creationDate;
        this.author = author;
        this.participants = participants;
        this.comments = comments;
    }

    
    public Date getSuggestedDate() {
        return suggestedDate;
    }

    public void setSuggestedDate(Date suggestedDate) {
        this.suggestedDate = suggestedDate;
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
