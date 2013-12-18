package cz.timepool.dto;

import java.util.Date;

/**
 *
 * @author   Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
public abstract class TemporalEntityDto extends AbstractDto {

	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
