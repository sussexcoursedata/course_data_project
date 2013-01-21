/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.business;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.purl.dc.elements._1.SimpleLiteral;

/**
 *
 * @author rjb41
 */
public class XCRI_CAPBusiness {

       
    public XCRI_CAPBusiness() {
        
    }


    public SimpleLiteral createSimpleLiteral(String value) {
        SimpleLiteral sl = new SimpleLiteral();
        sl.getContent().add(value);
        return sl;
    }

    /**
     * Returns and XML date for a given Date object
     * @param date
     * @return 
     */
    public XMLGregorianCalendar getXMLDate(Date date) {
        XMLGregorianCalendar xdate = null;
        GregorianCalendar c = new GregorianCalendar();
        try {
            if (date != null) {  
                c.setTime(date);
                xdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            } 
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(XCRI_CAPBusiness.class.getName()).log(Level.SEVERE, "Problem converting date to XML Date", ex);
        }


        return xdate;

    }
}
