//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.29 at 10:15:17 AM BST 
//


package org.purl.net.mlo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.purl.dc.elements._1.SimpleLiteral;
import org.w3c.dom.Element;


/**
 * <p>Java class for LearningOpportunityObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LearningOpportunityObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any processContents='lax' namespace='http://purl.org/dc/elements/1.1/' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://purl.org/net/mlo}url" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://purl.org/net/mlo}hasPart" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearningOpportunityObject", propOrder = {
    "any",
    "url",
    "hasPart"
})
@XmlSeeAlso({
    LearningOpportunitySpecification.class,
    LearningOpportunityProvider.class,
    LearningOpportunityInstance.class
})
public abstract class LearningOpportunityObject {

    @XmlAnyElement(lax = true)
    protected List<Object> any;
    protected List<SimpleLiteral> url;
    protected List<Association> hasPart;

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleLiteral }
     * 
     * 
     */
    public List<SimpleLiteral> getUrl() {
        if (url == null) {
            url = new ArrayList<SimpleLiteral>();
        }
        return this.url;
    }

    /**
     * Gets the value of the hasPart property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hasPart property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHasPart().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Association }
     * 
     * 
     */
    public List<Association> getHasPart() {
        if (hasPart == null) {
            hasPart = new ArrayList<Association>();
        }
        return this.hasPart;
    }

}
