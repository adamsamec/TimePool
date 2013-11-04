
package cz.timepool.dto;

import java.util.Date;

/**
 *
 * @author Lukas Lowinger
 */
public class CommentDto extends AbstractDto{
    private Long author;
    private Long term;
    private String text;
    private Date creationDate;

    public CommentDto(Long id, Long author, Long term, String text, Date creationDate) {
        this.id = id;
        this.author = author;
        this.term = term;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    
}
