//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.29 at 10:15:17 AM BST 
//


package org.xcri.profiles._1_2.catalog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xcri.profiles._1_2.catalog package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Provider_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "provider");
    private final static QName _AccreditedBy_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "accreditedBy");
    private final static QName _Presentation_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "presentation");
    private final static QName _Abbr_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "abbr");
    private final static QName _AwardedBy_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "awardedBy");
    private final static QName _Course_QNAME = new QName("http://xcri.org/profiles/1.2/catalog", "course");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xcri.profiles._1_2.catalog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VenueDType }
     * 
     */
    public VenueDType createVenueDType() {
        return new VenueDType();
    }

    /**
     * Create an instance of {@link TemporalDType }
     * 
     */
    public TemporalDType createTemporalDType() {
        return new TemporalDType();
    }

    /**
     * Create an instance of {@link CommonElements }
     * 
     */
    public CommonElements createCommonElements() {
        return new CommonElements();
    }

    /**
     * Create an instance of {@link DescriptionDType }
     * 
     */
    public DescriptionDType createDescriptionDType() {
        return new DescriptionDType();
    }

    /**
     * Create an instance of {@link CourseDType }
     * 
     */
    public CourseDType createCourseDType() {
        return new CourseDType();
    }

    /**
     * Create an instance of {@link VocabularyDType }
     * 
     */
    public VocabularyDType createVocabularyDType() {
        return new VocabularyDType();
    }

    /**
     * Create an instance of {@link ImageDType }
     * 
     */
    public ImageDType createImageDType() {
        return new ImageDType();
    }

    /**
     * Create an instance of {@link SubjectDType }
     * 
     */
    public SubjectDType createSubjectDType() {
        return new SubjectDType();
    }

    /**
     * Create an instance of {@link PresentationDType }
     * 
     */
    public PresentationDType createPresentationDType() {
        return new PresentationDType();
    }

    /**
     * Create an instance of {@link Catalog }
     * 
     */
    public Catalog createCatalog() {
        return new Catalog();
    }

    /**
     * Create an instance of {@link ProviderDType }
     * 
     */
    public ProviderDType createProviderDType() {
        return new ProviderDType();
    }

    /**
     * Create an instance of {@link CommonAndCommonDescriptiveElements }
     * 
     */
    public CommonAndCommonDescriptiveElements createCommonAndCommonDescriptiveElements() {
        return new CommonAndCommonDescriptiveElements();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "provider")
    public JAXBElement<ProviderDType> createProvider(ProviderDType value) {
        return new JAXBElement<ProviderDType>(_Provider_QNAME, ProviderDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "accreditedBy")
    public JAXBElement<String> createAccreditedBy(String value) {
        return new JAXBElement<String>(_AccreditedBy_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PresentationDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "presentation")
    public JAXBElement<PresentationDType> createPresentation(PresentationDType value) {
        return new JAXBElement<PresentationDType>(_Presentation_QNAME, PresentationDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "abbr")
    public JAXBElement<String> createAbbr(String value) {
        return new JAXBElement<String>(_Abbr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "awardedBy")
    public JAXBElement<String> createAwardedBy(String value) {
        return new JAXBElement<String>(_AwardedBy_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CourseDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xcri.org/profiles/1.2/catalog", name = "course")
    public JAXBElement<CourseDType> createCourse(CourseDType value) {
        return new JAXBElement<CourseDType>(_Course_QNAME, CourseDType.class, null, value);
    }

}
