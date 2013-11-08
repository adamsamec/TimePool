
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
    
    
}
