/*
 * Copyright (c) 2012, University of Sussex and/or its affiliates. All rights reserved.
 */
package uk.ac.susx.xcricap.session;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * SOAP XCRI-CAP Service
 * @author rjb41
 */
@WebService(serviceName = "CourseService")
public class XRI_CAPService {

    @EJB
    XCRI_CAPSession xcriSession;

    /**
     * Produces SOAP XCRI-CAP Service
     * @return 
     */
    @WebMethod(operationName = "getCourses")
    @WebResult(name = "catalog")
    public Object getCourses() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            if (!(new File(xcriSession.getCourseFileName())).exists()) {
                xcriSession.createCoursesFile(false);
            }
            doc = dBuilder.parse(new File(xcriSession.getCourseFileName()));
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XRI_CAPService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XRI_CAPService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XRI_CAPService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doc != null) {
            return doc.getDocumentElement();
        }
        return null;
    }
}
