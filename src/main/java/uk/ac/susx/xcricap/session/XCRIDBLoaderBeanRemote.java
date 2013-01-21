package uk.ac.susx.xcricap.session;

import java.util.List;
import javax.ejb.Remote;
import uk.ac.susx.xcricap.model.XcriCatalog;

/**
 *
 * @author rjb41
 */
@Remote
public interface XCRIDBLoaderBeanRemote {
    
    public List<XcriCatalog> loadCatalogs(String catalogType);
    
}
