package uk.ac.susx.xcricap.session;

import javax.ejb.Remote;
import org.xcri.profiles._1_2.catalog.Catalog;

/**
 *
 * @author rjb41
 */
@Remote
public interface CatalogueLoaderBeanRemote {
        
    Catalog retrieveCatalog(String catalogType);
}
