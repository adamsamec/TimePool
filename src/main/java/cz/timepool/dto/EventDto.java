
package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
class EventDto extends AbstractDto{
    private String author;
    private String title;
    private String location;
    private String description;
    private String settings;
    private Date creationDate;
    private List<Long> tags;
    private List<Long> users;
    private List<Long> terms;

    public EventDto(Long id, String author, String title, String location, String description, String settings, Date creationDate, List<Long> tags, List<Long> users, List<Long> terms) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.location = location;
        this.description = description;
        this.settings = settings;
        this.creationDate = creationDate;
        this.tags = tags;
        this.users = users;
        this.terms = terms;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
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

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public List<Long> getTerms() {
        return terms;
    }

    public void setTerms(List<Long> terms) {
        this.terms = terms;
    }
    
    
}
