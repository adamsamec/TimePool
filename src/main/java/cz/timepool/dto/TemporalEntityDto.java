package cz.timepool.dto;

import java.util.Date;

/**
 *
 * @author Adam Samec <adam.smec@gmail.com>
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
