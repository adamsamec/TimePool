
package cz.timepool.service;

import cz.timepool.dto.TagDto;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public interface TagService {
    
    public Long addTagToEvent(String text, Long event);
    //public Long addTag(String text);
    public List<TagDto> getAllTags();
    public void deleteTag(Long tag);
}
