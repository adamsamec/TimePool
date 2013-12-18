
package cz.timepool.helper;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public class DtoTransformerHelper {
    /**
     * Convert list of entities to list of identifiers
     * @param list list to be converted
     * @return list of identifiers, null - if list is null
     */
    public static List<Long> getIdentifiers(List<? extends AbstractBusinessObject> list) {
        if (list == null) {
            return null;
        }
        List<Long> ids = new ArrayList<Long>();

        for (AbstractBusinessObject abo : list) {
            ids.add(abo.getId());
        }
        return ids;
    }
}