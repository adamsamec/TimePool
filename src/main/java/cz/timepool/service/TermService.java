
package cz.timepool.service;

import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public interface TermService {
    //TODO: addTermToEvent, deleteTermById, editTermById, getTermByEventId, changeTermStatusById, addParticipantToTermById 
    public Long addTermToEvent(Date date, String status, String description, Date creationDate, Long author, Long event);
    public void deleteTermById (Long idTerm);
    public void editTermById (TermDto changedTerm, Long idTerm);
    public List<TermDto> getTermsByEventId(Long idEvent);
    public void changeTermStatusById (String status, Long termId);
    public void addParticipantToTermById (Long idParticipant, Long idTerm);
    
}
