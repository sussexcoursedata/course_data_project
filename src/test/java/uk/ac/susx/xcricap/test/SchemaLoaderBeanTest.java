package uk.ac.susx.xcricap.test;

import java.util.logging.Logger;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.Properties;
import javax.naming.InitialContext;
import uk.ac.susx.xcricap.session.SchemaLoaderBeanRemote;
import uk.ac.susx.xcricap.model.XcriCatalog;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rjb41
 */
public class SchemaLoaderBeanTest {

    public SchemaLoaderBeanTest() {
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

    /**
     * Test of populateSchema method, of class SchemaLoaderBean.
     */
    @Test
    public void testPopulateSchema() throws Exception {
//        System.out.println("populateSchema");
//        SchemaLoaderBeanRemote instance = (SchemaLoaderBeanRemote)getInitialContext().lookup("uk.ac.susx.xcricap.session.SchemaLoaderBeanRemote");
//        int catID = 0;
//        try {
//            List<XcriCatalog> cat = instance.loadCatalogs("MAIN");
//            catID = cat.get(0).getCatalogId().toBigInteger().intValue();
//        } catch (ArrayIndexOutOfBoundsException e) {
//        }
//
//        instance.populateSchema();
//        List<XcriCatalog> cat = instance.loadCatalogs("MAIN");
//        if (catID == cat.get(0).getCatalogId().toBigInteger().intValue()) {
//            fail("Catalog id was the same, has not been updated");
//        }

    }

    /**
     * Test of populateSchema method, of class SchemaLoaderBean.
     */
    @Test
    public void testPopulateSearchableSchema() throws Exception {
//        System.out.println("populateSearchableSchema");
//        SchemaLoaderBeanRemote instance = (SchemaLoaderBeanRemote)getInitialContext().lookup("uk.ac.susx.xcricap.session.SchemaLoaderBeanRemote");
//        int catID = 0;
//        try {
//            List<XcriCatalog> cat = instance.loadCatalogs("SEARCHABLE");
//            catID = cat.get(0).getCatalogId().toBigInteger().intValue();
//        } catch (ArrayIndexOutOfBoundsException e) {
//        }
//
//        instance.populateSearchableSchema();
//        List<XcriCatalog> cat = instance.loadCatalogs("SEARCHABLE");
//        if (catID == cat.get(0).getCatalogId().toBigInteger().intValue()) {
//            fail("Catalog id was the same, has not been updated");
//        }

    }

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
