package uk.ac.susx.xcricap.session;


import javax.ejb.Remote;
import org.w3c.dom.Document;


/**
 *
 * @author rjb41
 */
@Remote
public interface XCRI_CAPSessionRemote {
    
     public void createCoursesFile(boolean searchable);
     public Document searchCatalog(String elementName, String elementValue);
     public Document loadCatalogFromFile(boolean searchable);
     public void createMainCoursesFile();
     public void populateXCRIDatabase();
     public byte[] loadCourseDirectoryFile(String fileName);
}
