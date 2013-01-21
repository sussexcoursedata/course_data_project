package uk.ac.susx.xcricap.test;

import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xcri.profiles._1_2.catalog.Catalog;
import uk.ac.susx.xcricap.session.CatalogueLoaderBeanRemote;

/**
 *
 * @author rjb41
 */
public class CatalogueLoaderBeanTest {

    public CatalogueLoaderBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of retrieveCatalog method, of class CatalogueLoaderBean.
//     */
//    @Test
//    public void testRetrieveCatalog() throws Exception {
//        System.out.println("retrieveCatalog");
//        CatalogueLoaderBeanRemote result = (CatalogueLoaderBeanRemote)getInitialContext().lookup("uk.ac.susx.xcricap.session.CatalogueLoaderBeanRemote");
//        String catalogType = "MAIN";
//        Catalog expResult = result.retrieveCatalog(catalogType);
//        assertNotNull(expResult.getProvider().get(0));
//
//    }
//
//    @Test
//    public void testCatalogIsValid() throws Exception {
//        System.out.println("validateCatalog");
//        CatalogueLoaderBeanRemote result = (CatalogueLoaderBeanRemote)getInitialContext().lookup("uk.ac.susx.xcricap.session.CatalogueLoaderBeanRemote");
//        String catalogType = "MAIN";
//        Catalog expResult = result.retrieveCatalog(catalogType);
//
//        Document doc;
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        dbf.setNamespaceAware(true);
//        doc = dbf.newDocumentBuilder().newDocument();
//
//        JAXBContext context = JAXBContext.newInstance(expResult.getClass());
//        context.createJAXBIntrospector();
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://xcri.org/profiles/1.2/catalog http://www.xcri.co.uk/bindings/xcri_cap_1_2.xsd http://xcri.org/profiles/1.2/catalog/terms  http://www.xcri.co.uk/bindings/xcri_cap_terms_1_2.xsd http://xcri.co.uk http://www.xcri.co.uk/bindings/coursedataprogramme.xsd");
//        marshaller.marshal(expResult, doc);
//
//
//        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        URL schemaURL = new URL("http://www.xcri.co.uk/bindings/coursedataprogramme.xsd");// The URL to your XML Schema; 
//        Schema schema = sf.newSchema(schemaURL);
//        Validator validator = schema.newValidator();
//        DOMSource source = new DOMSource(doc);
//        validator.validate(source);
//
//
//    }

    private InitialContext getInitialContext() {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext ic = null;
        try {
            ic = new InitialContext(props);
        } catch (NamingException ex) {
            Logger.getLogger(XCRI_CAPTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ic;
    }
}
