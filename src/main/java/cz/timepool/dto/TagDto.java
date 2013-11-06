
package cz.timepool.dto;

/**
 *
 * @author Lukas Lowinger
 */
public class TagDto extends AbstractDto {
    private String text;
    private Long event;

    public TagDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }
    
    
}
