//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.29 at 09:03:32 AM BST 
//


package org.xcri.profiles._1_2.catalog.terms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.purl.dc.elements._1.DescriptionDType;


/**
 * Description refinement typically used to clarify resources that would be provided to learners by the institution
 * 
 * <p>Java class for providedResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="providedResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://purl.org/dc/elements/1.1/}descriptionDType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/1999/xhtml}div" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "providedResource")
public class ProvidedResource
    extends DescriptionDType
{


}
