
package cz.timepool.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class EventDto extends AbstractDto{
    private Long author;
    private String title;
    private String location;
    private String description;
    private Date creationDate;
    private List<Long> tags;
    private List<Long> terms;

    
}
