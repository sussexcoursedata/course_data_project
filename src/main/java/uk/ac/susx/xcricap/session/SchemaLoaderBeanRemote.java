package uk.ac.susx.xcricap.session;

import javax.ejb.Remote;

/**
 *
 * @author rjb41
 */
@Remote
public interface SchemaLoaderBeanRemote {
    
    public void populateSchema();
    public void populateSearchableSchema();

    
}
