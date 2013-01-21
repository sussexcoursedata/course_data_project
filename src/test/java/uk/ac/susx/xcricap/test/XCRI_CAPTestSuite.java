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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rjb41
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({uk.ac.susx.xcricap.test.XCRI_CAPSessionRemoteTest.class,
                     uk.ac.susx.xcricap.test.SchemaLoaderBeanTest.class, 
                     uk.ac.susx.xcricap.test.CatalogueLoaderBeanTest.class
                    })
public class XCRI_CAPTestSuite {

    public static InitialContext initialContext;

    @BeforeClass
    public static void setUpClass() throws Exception {
        initialContext = getInitialContext();
    }



    private static InitialContext getInitialContext() {
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

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
