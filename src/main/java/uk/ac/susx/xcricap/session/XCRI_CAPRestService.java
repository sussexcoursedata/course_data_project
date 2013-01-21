package uk.ac.susx.xcricap.session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * REST Web Service
 *
 * @author rjb41
 */
@Path("/")
@Stateless
public class XCRI_CAPRestService {

    @EJB
    XCRI_CAPSession xcriSession;
    @Context
    private UriInfo context;

    /** Creates a new instance of XCRI_CAPRestService */
    public XCRI_CAPRestService() {
    }

    /**
     * Returns the REST XML representation of the service 
     * @return 
     */
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_XML)
    public StreamingOutput getXCRIXml() {
        return new StreamingOutput() {

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                byte[] out = xcriSession.loadCourseDirectoryFile(xcriSession.getCourseFileName());
                output.write(out);
            }
        };
    }

    /**
     * Returns the REST XML representation of the service 
     * @todo specify search criteria
     * @param cId
     * @return 
     */
    @GET
    @Path("/rest/{name}/{value}")
    @Produces(MediaType.TEXT_XML)
    public String getXCRIXml(@PathParam("name") String name, @PathParam("value") String value) {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        StringWriter buffer = new StringWriter();
        try {
            transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new DOMSource(xcriSession.searchCatalog(name, value)), new StreamResult(buffer));
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XCRI_CAPRestService.class.getName()).log(Level.SEVERE, "Problem outputting document", ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XCRI_CAPRestService.class.getName()).log(Level.SEVERE, "Problem outputting document", ex);
        }
        return buffer.toString();


    }

    /**
     * Returns the JSON representation of the service 
     * @return 
     */
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXCRIJson() {
        if (!(new File(xcriSession.getCourseFileName())).exists()) {
            xcriSession.createCoursesFile(false);
        }
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(xcriSession.getJSONCourses().toString());
        return gson.toJson(je);
    }
}
