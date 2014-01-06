package cz.timepool.bo;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author   Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
@MappedSuperclass
public abstract class TemporalEntity extends AbstractBusinessObject {

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	protected Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
