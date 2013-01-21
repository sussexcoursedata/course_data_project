/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.test;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import uk.ac.susx.xcricap.session.XCRI_CAPSessionRemote;

/**
 *
 * @author rjb41
 */
public class XCRI_CAPSessionRemoteTest {

    public XCRI_CAPSessionRemoteTest() {
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
//     * Test of createCoursesFile method, of class XCRI_CAPSessionRemote.
//     */
//    @Test
//    public void testCreateCoursesFile() {
//        System.out.println("createCoursesFile");
//        boolean searchable = false;
//
//        XCRI_CAPSessionRemote instance = null;
//        try {
//            instance = (XCRI_CAPSessionRemote) getInitialContext().lookup("uk.ac.susx.xcricap.session.XCRI_CAPSessionRemote");
//        } catch (NamingException ex) {
//            Logger.getLogger(XCRI_CAPSessionRemoteTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        instance.createCoursesFile(searchable);
//
//
//    }
    
//    @Test
//    public void testPopulateXCRIDatabase() {
//        System.out.println("createCoursesFile");
//        boolean searchable = false;
//
//        XCRI_CAPSessionRemote instance = null;
//        try {
//            instance = (XCRI_CAPSessionRemote) getInitialContext().lookup("uk.ac.susx.xcricap.session.XCRI_CAPSessionRemote");
//        } catch (NamingException ex) {
//            Logger.getLogger(XCRI_CAPSessionRemoteTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        instance.populateXCRIDatabase();
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
//    /**
//     * Test of searchCatalog method, of class XCRI_CAPSessionRemote.
//     */
//    @Test
//    public void testSearchCatalog() {
//        System.out.println("searchCatalog");
//        String elementName = "";
//        String elementValue = "";
//        XCRI_CAPSessionRemote instance = new XCRI_CAPSessionRemoteImpl();
//        Document expResult = null;
//        Document result = instance.searchCatalog(elementName, elementValue);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of loadCatalogFromFile method, of class XCRI_CAPSessionRemote.
//     */
//    @Test
//    public void testLoadCatalogFromFile() {
//        System.out.println("loadCatalogFromFile");
//        boolean searchable = false;
//        XCRI_CAPSessionRemote instance = new XCRI_CAPSessionRemoteImpl();
//        Document expResult = null;
//        Document result = instance.loadCatalogFromFile(searchable);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadCourseDirectoryFile method, of class XCRI_CAPSessionRemote.
//     */
//    @Test
//    public void testLoadCourseDirectoryFile() {
//        System.out.println("loadCourseDirectoryFile");
//        String fileName = "";
//        XCRI_CAPSessionRemote instance = new XCRI_CAPSessionRemoteImpl();
//        byte[] expResult = null;
//        byte[] result = instance.loadCourseDirectoryFile(fileName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
