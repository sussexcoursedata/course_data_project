/*
 * Copyright (c) 2012, University of Sussex and/or its affiliates. All rights reserved.
 */
package uk.ac.susx.xcricap.session;

import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xcri.profiles._1_2.catalog.Catalog;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Session bean for marshaling representations of the feed
 * @author rjb41
 */
@Stateless
@LocalBean
public class XCRI_CAPSession implements XCRI_CAPSessionRemote {

    @EJB
    CatalogueLoaderBeanRemote catalogloaderBean;
    @Resource(name = "schemaLoaderBeanName")
    String schemaLoaderBeanName;
    @Resource(name = "useLoaderBean")
    boolean useLoaderBean;
    private final String courseFileName = "CourseCatalog.xml";
    private final String searchCourseFileName = "SearchCourseCatalog.xml";

    @Override
    public Document loadCatalogFromFile(boolean searchable) {
        String fileName = courseFileName;
        Document doc = null;
        if (searchable) {
            fileName = courseFileName;//searchCourseFileName;
        }
        if (!(new File(fileName).exists())) {
            createCoursesFile(searchable);
        }
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            doc = dbf.newDocumentBuilder().parse(new File(fileName));
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;

    }

    /**
     * Reads the generated course directory XML file. If it doesn't exist it forces the file to be
     * created
     * @return 
     */
    @Override
    public byte[] loadCourseDirectoryFile(String fileName) {
        if (!(new File(fileName)).exists()) {
            createCoursesFile(false);
        }
        RandomAccessFile f;
        byte[] b = null;
        try {
            f = new RandomAccessFile(fileName, "r");
            b = new byte[(int) f.length()];
            f.read(b);
        } catch (IOException ex) {
            Logger.getLogger(XCRI_CAPRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    private void appendComment(String filename) {
        String comment = "<!--This work is licensed under the Creative Commons Attribution 3.0 License. To view a copy of this license, visit http://creativecommons.org/licenses/by/3.0/ or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.-->";
        try {
            StringBuilder fileData = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            char[] buf = new char[1024];
            int numRead = 0;

            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            FileOutputStream fos = new FileOutputStream(filename);
            fos.write(fileData.toString().replace("?>", "?>" + comment).getBytes());
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Schedule(second = "0", minute = "0", hour = "3", persistent = false)
    @Override
    public void createMainCoursesFile() {
        createCoursesFile(false);
    }

    @Override
    @Schedule(second = "0", minute = "0", hour = "2", persistent = false)
    public void populateXCRIDatabase() {
        if (useLoaderBean) {
            SchemaLoaderBeanRemote schemaLoaderBean;
            try {
                schemaLoaderBean = (SchemaLoaderBeanRemote) new InitialContext().lookup(schemaLoaderBeanName);
                schemaLoaderBean.populateSchema();
            } catch (NamingException ex) {
                Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Process to produce flat XCRI-CAP file
     */
    @Override
    public void createCoursesFile(boolean searchable) {
        Catalog cat = createXCRICatalog(searchable);
        if(cat.getProvider() == null || cat.getProvider().isEmpty()) {
            populateXCRIDatabase();
            cat = createXCRICatalog(searchable);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(cat.getClass());
            context.createJAXBIntrospector();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://xcri.org/profiles/1.2/catalog http://www.xcri.co.uk/bindings/xcri_cap_1_2.xsd http://xcri.org/profiles/1.2/catalog/terms  http://www.xcri.co.uk/bindings/xcri_cap_terms_1_2.xsd http://xcri.co.uk http://www.xcri.co.uk/bindings/coursedataprogramme.xsd");
            FileOutputStream fos = null;
            String fileName = "";
            if (searchable) {
                fileName = searchCourseFileName;
            } else {
                fileName = courseFileName;

            }
            fos = new FileOutputStream(fileName);
            marshaller.marshal(cat, fos);
            fos.close();
            appendComment(fileName);
        } catch (JAXBException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, "Problem getting courses", ex);
        } catch (Exception ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, "Problem getting courses", ex);
        }

    }

    /**
     * Unmarshalls XML file to Catalog then re marshalls as json
     * @return 
     */
    public StringWriter getJSONCourses() {

        Catalog cat = new Catalog();
        StringWriter out = new StringWriter();

        try {
            JAXBContext catContext = JAXBContext.newInstance(cat.getClass());
            Unmarshaller u = catContext.createUnmarshaller();
            File f = new File(courseFileName);
            cat = (Catalog) u.unmarshal(f);
            catContext.createJAXBIntrospector();
            Marshaller marshaller = catContext.createMarshaller();
            JSONMarshaller jmarshaller = JSONJAXBContext.getJSONMarshaller(marshaller);
            jmarshaller.setProperty(JSONMarshaller.FORMATTED, true);
            jmarshaller.marshallToJSON(cat, out);

        } catch (JAXBException ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, "Problem getting json courses", ex);
        } catch (Exception ex) {
            Logger.getLogger(XCRI_CAPSession.class.getName()).log(Level.SEVERE, "Problem getting json courses", ex);
        }

        return out;
    }

    /**
     * Creates a course catalog using CatalogueLoaderBeanRemote
     * 
     * @return org.xcri.profiles._1_2.catalog.Catalog
     * @see uk.ac.susx.xcricap.session.CatalogueLoaderBean
     */
    protected Catalog createXCRICatalog(boolean searchable) {
        Catalog cat = null;
        if (searchable) {
            cat = catalogloaderBean.retrieveCatalog("SEARCHABLE");
        } else {
            cat = catalogloaderBean.retrieveCatalog("MAIN");
        }
        return cat;
    }

    public String getCourseFileName() {
        return courseFileName;
    }

    public String getSearchCourseFileName() {
        return searchCourseFileName;
    }

    @Override
    public Document searchCatalog(String elementName, String elementValue) {
        Document searchCatalog = loadCatalogFromFile(true);
        if (elementName != null && elementValue != null) {
            searchDocumentRecursively(searchCatalog, elementName, elementValue);
        }
        return searchCatalog;
    }

    /**
     * Removes the parent node for the course owning the node passed in.
     * @param node 
     */
    private void removeParentCourse(Node node) {
        String parentName = node.getParentNode().getNodeName();
        if (parentName.equals("course")) {
            if (node.getParentNode().getParentNode() != null) {
                node.getParentNode().getParentNode().removeChild(node.getParentNode());
            }
        } else if (parentName.equals("mlo:qualification") || parentName.equals("presentation")) {
            removeParentCourse(node.getParentNode());
        }

    }

    /**
     * 
     * @param node
     * @return 
     */
    private boolean nodeExcludable(Node node) {
        return !(node.getNodeName().equals("dc:title") && node.getParentNode().getNodeName().equals("mlo:qualification"));
    }

    /**
     * Passes through XML, removes nodes belonging to course (and sub nodes) and removes courses for the 
     * node name specified where the text does not contain elementValue
     * @param node
     * @param elementName
     * @param elementValue 
     */
    private void searchDocumentRecursively(Node node, String elementName, String elementValue) {
        if (node.getNodeName().toUpperCase().endsWith(elementName.toUpperCase())) {
            if (!node.getTextContent().toUpperCase().contains(elementValue.toUpperCase())) {
                if (nodeExcludable(node)) {
                    removeParentCourse(node);
                }
            }
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                searchDocumentRecursively(currentNode, elementName, elementValue);
            }
        }
    }
}
