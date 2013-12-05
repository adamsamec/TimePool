package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dto.TermDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas Lowinger
 */
@Component
class TermServiceImpl extends AbstractDataAccessService implements TermService {

	@Override
	public TermDto getTermById(Long termId) {
		Term term = this.timepoolDao.getById(termId, Term.class);
		TermDto eventDto = new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors()), DtoTransformerHelper.getIdentifiers(term.getComments()));
		return eventDto;
	}

	@Override
	public List<TermDto> getTermsByEventId(Long idEvent) {
		Event event = this.timepoolDao.loadById(idEvent, Event.class);
		List<Term> terms = event.getTerms();
		List<TermDto> termsDto = new ArrayList<TermDto>();
		for (Term term : terms) {
			termsDto.add(new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors()), DtoTransformerHelper.getIdentifiers(term.getComments())));
		}
		return termsDto;
	}

	@Override
	public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long author, Long event) {
		Term term = new Term();
		term.setCreationDate(creationDate);
		term.setTermDate(termDate);
		term.setDescription(description);
		term.setStatus(status);
		term.setAuthor(this.timepoolDao.loadById(author, User.class));
		term.setEvent(this.timepoolDao.loadById(event, Event.class));
		return this.timepoolDao.save(term).getId();
	}

	@Override
	public void deleteTermById(Long idTerm) {
//        genericDao.removeById(idTerm, Term.class);
		Term term = this.timepoolDao.loadById(idTerm, Term.class);
		Event event = term.getEvent();
		event.removeTerm(term);
	}

	@Override
	public void editTermById(TermDto changedTerm, Long idTerm) {
		Term term = new Term();
		term.setCreationDate(changedTerm.getCreationDate());
		term.setTermDate(changedTerm.getTermDate());
		term.setDescription(changedTerm.getDescription());
		term.setStatus(changedTerm.getStatus());
		term.setAuthor(this.timepoolDao.loadById(changedTerm.getAuthor(), User.class));
//        term.setAcceptors(DtoTransformerHelper.getIdentifiers(changedTerm.get));
		timepoolDao.save(term);
	}

	@Override
	public void changeTermStatusById(StatusEnum status, Long termId) {
		Term term = this.timepoolDao.loadById(termId, Term.class);
		term.setStatus(status);
		timepoolDao.save(term);
	}

	@Override
	public void addAcceptorToTermById(Long idAcceptor, Long idTerm) {
		User acceptor = this.timepoolDao.loadById(idAcceptor, User.class);
		Term term = this.timepoolDao.loadById(idTerm, Term.class);
		term.addAcceptor(acceptor);
		timepoolDao.save(term);
	}

}
