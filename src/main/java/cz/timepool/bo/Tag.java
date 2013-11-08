
package cz.timepool.bo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Tag extends AbstractBusinessObject{
    @Column (nullable=false)
    private String text;
    
    @ManyToMany( cascade= CascadeType.REMOVE,mappedBy="tags")
    private List<Event> events;

    public void addEvent(Event e){
        if(events == null ){
            events = new ArrayList<Event>();
        }
        if(!events.contains(e)){
            events.add(e);
        }
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
   
}
