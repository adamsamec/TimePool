
package cz.timepool.dto;

/**
 *
 * @author Lukas Lowinger
 */
public class TagDto extends AbstractDto {
    private String text;
    private Long event;

    public TagDto(Long event, String text) {
        this.event = event;
        this.text = text;
    }
    
    
}
