
package cz.timepool.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Lukas Lowinger
 */
@Entity
public class Tag extends AbstractBusinessObject{
    @Column (nullable=false)
    private String text;
    
    
}
