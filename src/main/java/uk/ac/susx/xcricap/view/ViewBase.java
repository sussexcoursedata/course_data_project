package uk.ac.susx.xcricap.view;

import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import uk.ac.susx.xcricap.session.XCRI_CAPSessionRemote;

/**
 * Base class to output XSL transformation of feed
 * @author rjb41
 */

public class ViewBase extends HttpServlet {
    
    @EJB
    XCRI_CAPSessionRemote xcriSession;

    protected void outputXSL(PrintWriter out, String xslFile){
    
        try {
            Document doc = xcriSession.loadCatalogFromFile(false);
            String path = getServletContext().getRealPath("/WEB-INF/xsl/");
            Transformer t = null;
            Source xmlSource = new DOMSource(doc);
            try {
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Source xslSource = new StreamSource(new File(path, xslFile));
                t = tFactory.newTransformer(xslSource);
                t.transform(xmlSource, new StreamResult(out));
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(ViewCatalog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerFactoryConfigurationError ex) {
                Logger.getLogger(ViewCatalog.class.getName()).log(Level.SEVERE, null, ex);

            } catch (TransformerException ex) {
                Logger.getLogger(ViewCatalog.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            out.close();
        }
    }

}
