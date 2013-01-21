package uk.ac.susx.xcricap.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBElement;
import org.purl.dc.elements._1.DescriptionDType;
import org.purl.dc.elements._1.SimpleLiteral;
import org.purl.dc.elements._1.SubjectDType;
import org.purl.net.mlo.CreditDType;
import org.purl.net.mlo.LocationDType;
import org.purl.net.mlo.QualificationDType;
import org.purl.net.mlo.Resource;
import org.purl.net.mlo.StartDType;

import org.xcri.profiles._1_2.catalog.Catalog;
import org.xcri.profiles._1_2.catalog.CourseDType;
import org.xcri.profiles._1_2.catalog.ImageDType;
import org.xcri.profiles._1_2.catalog.PresentationDType;
import org.xcri.profiles._1_2.catalog.ProviderDType;
import org.xcri.profiles._1_2.catalog.TemporalDType;
import org.xcri.profiles._1_2.catalog.VenueDType;
import org.xcri.profiles._1_2.catalog.VocabularyDType;
import uk.ac.susx.xcricap.business.XCRI_CAPBusiness;
import uk.ac.susx.xcricap.model.XcriAddress;
import uk.ac.susx.xcricap.model.XcriCapCourseSubject;
import uk.ac.susx.xcricap.model.XcriCatalog;
import uk.ac.susx.xcricap.model.XcriCourse;
import uk.ac.susx.xcricap.model.XcriCourseIdentifier;
import uk.ac.susx.xcricap.model.XcriCredit;
import uk.ac.susx.xcricap.model.XcriLocation;
import uk.ac.susx.xcricap.model.XcriPresentation;
import uk.ac.susx.xcricap.model.XcriPresentationEngagement;
import uk.ac.susx.xcricap.model.XcriPresentationPlaces;
import uk.ac.susx.xcricap.model.XcriProvider;
import uk.ac.susx.xcricap.model.XcriPrsntLangOfAssess;
import uk.ac.susx.xcricap.model.XcriPrsntLangOfInstruction;
import uk.ac.susx.xcricap.model.XcriQualification;
import uk.co.xcri.Ukprn;

/**
 * Class to populate JAXB classes from database. Produces a fully populated Catalog object
 * @see org.xcri.profiles._1_2.catalog.Catalog
 * @author rjb41
 */
@Stateless
@Remote
public class CatalogueLoaderBean implements CatalogueLoaderBeanRemote {

    @PersistenceContext(unitName = "XCRI-CAPPU")
    private EntityManager em;
    @EJB
    XCRIDBLoaderBean dbLoader;

    @Override
    public Catalog retrieveCatalog(String catalogType) {
        XcriCatalog dbCat = loadCatalog(catalogType);
        Catalog cat = null;
        if (dbCat != null) {
            cat = loadXCRIDocument(dbCat);
        } else {
            cat = new Catalog();
        }

        return cat;
    }

    /**
     * Returns a populated list of Courses
     * @param dbProvider
     * @return 
     */
    private List<CourseDType> loadXCRICourses(XcriProvider dbProvider) {
        List<XcriCourse> dbCourses = dbProvider.getXcriCourseList();
        List<CourseDType> courses = new ArrayList<CourseDType>();
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        org.purl.dc.elements._1.ObjectFactory elemFactory = new org.purl.dc.elements._1.ObjectFactory();
        org.xcri.profiles._1_2.catalog.ObjectFactory xcriFactory = new org.xcri.profiles._1_2.catalog.ObjectFactory();

        for (XcriCourse dbCourse : dbCourses) {
            CourseDType cdt = xcriFactory.createCourseDType();
            cdt.getTitle().add(elemFactory.createTitle(xcb.createSimpleLiteral(dbCourse.getTitle())));

            if (dbCourse.getDescription() != null) {
                DescriptionDType description = new DescriptionDType();
                description.getContent().add(dbCourse.getDescription());
                cdt.getDescription().add(elemFactory.createDescription(description));
            }

            org.xcri.profiles._1_2.catalog.DescriptionDType applyToDesc = xcriFactory.createDescriptionDType();
            applyToDesc.setHref(dbCourse.getApplicationProcedure());
            cdt.getApplicationProcedure().add(applyToDesc);
            cdt.setUrl(xcb.createSimpleLiteral(dbCourse.getUrl()));
            cdt.getIdentifier().addAll(loadCourseIdentifiers(dbCourse));
            cdt.getSubject().addAll(loadCourseSubjects(dbCourse));
            cdt.getPresentation().addAll(loadPresentation(dbCourse));
            cdt.getQualification().addAll(loadCourseQualification(dbCourse));
            cdt.getCredit().addAll(loadCredit(dbCourse));

            if (dbCourse.getC_abstract() != null) {
                org.xcri.profiles._1_2.catalog.DescriptionDType abstractDesc = xcriFactory.createDescriptionDType();
                abstractDesc.setHref(dbCourse.getC_abstract());
                abstractDesc.setLang("en");
                cdt.getAbstract().add(abstractDesc);
            }
            courses.add(cdt);

        }
        return courses;
    }

    /**
     * Returns a populated list of credits
     * @param dbCourse
     * @return 
     */
    private List<CreditDType> loadCredit(XcriCourse dbCourse) {
        List<CreditDType> credit = new ArrayList<CreditDType>();
        org.purl.net.mlo.ObjectFactory mloFactory = new org.purl.net.mlo.ObjectFactory();


        for (XcriCredit dbCredit : dbCourse.getXcriCreditList()) {
            CreditDType cdt = mloFactory.createCreditDType();
            cdt.getLevel().add(dbCredit.getCreditLevel());
            cdt.getScheme().add(dbCredit.getCreditValue());
            cdt.getValue().add(dbCredit.getCreditValue());
            credit.add(cdt);
        }
        return credit;
    }

    /**
     * Returns a populated list of qualifications
     * @param dbCourse
     * @return 
     */
    private List<QualificationDType> loadCourseQualification(XcriCourse dbCourse) {
        org.purl.net.mlo.ObjectFactory mloFactory = new org.purl.net.mlo.ObjectFactory();
        org.purl.dc.elements._1.ObjectFactory elemFactory = new org.purl.dc.elements._1.ObjectFactory();
        List<QualificationDType> qualList = new ArrayList<QualificationDType>();
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        for (XcriQualification dbQual : dbCourse.getXcriQualificationList()) {
            QualificationDType qual = mloFactory.createQualificationDType();
            qual.setAbbr(dbQual.getAbbreviation());
            qual.setAccreditedBy(dbQual.getAccreditedBy());
            qual.setAwardedBy(dbQual.getAwardedBy());
            qual.setEducationLevel(xcb.createSimpleLiteral(dbQual.getEducationLevel()));

            SimpleLiteral idLit = elemFactory.createSimpleLiteral();
            idLit.setLang("en");
            idLit.getContent().add(dbQual.getIdentifier());
            qual.getIdentifier().add(elemFactory.createIdentifier(idLit));

            SimpleLiteral titleLit = elemFactory.createSimpleLiteral();
            titleLit.setLang("en");
            titleLit.getContent().add(dbQual.getTitle());
            qual.getTitle().add(elemFactory.createTitle(titleLit));

            SimpleLiteral typeLit = elemFactory.createSimpleLiteral();
            typeLit.setLang("en");
            typeLit.getContent().add(dbQual.getType());
            qual.setType(elemFactory.createType(typeLit));
            qual.setUrl(xcb.createSimpleLiteral(dbQual.getUrl()));
            qualList.add(qual);
        }
        return qualList;
    }

    /**
     * Returns a populated list of presentations
     * @param dbCourse
     * @return 
     */
    private List<PresentationDType> loadPresentation(XcriCourse dbCourse) {
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        List<PresentationDType> presentations = new ArrayList<PresentationDType>();
        org.xcri.profiles._1_2.catalog.ObjectFactory xcriFactory = new org.xcri.profiles._1_2.catalog.ObjectFactory();
        org.purl.net.mlo.ObjectFactory mloFactory = new org.purl.net.mlo.ObjectFactory();
        org.purl.dc.elements._1.ObjectFactory dcFactory = new org.purl.dc.elements._1.ObjectFactory();
        for (XcriPresentation dbPres : dbCourse.getXcriPresentationList()) {
            PresentationDType pdt = xcriFactory.createPresentationDType();
            pdt.setAge(dbPres.getAge());

            if (dbPres.getApplyFrom() != null) {
                TemporalDType applyFrom = xcriFactory.createTemporalDType();
                applyFrom.setContent(xcb.getXMLDate(dbPres.getApplyFrom()).toString());
                pdt.setApplyFrom(applyFrom);
            }

            pdt.setApplyTo(dbPres.getApplyTo());

            if (dbPres.getApplyUntil() != null) {
                TemporalDType applyUntil = xcriFactory.createTemporalDType();
                applyUntil.setContent(xcb.getXMLDate(dbPres.getApplyUntil()).toString());
                pdt.setApplyUntil(applyUntil);
            }

            VocabularyDType aModeVocab = xcriFactory.createVocabularyDType();
            aModeVocab.setIdentifier(dbPres.getAttendanceModeIdentifier());
            aModeVocab.setContent(dbPres.getAttendanceMode());
            pdt.setAttendanceMode(aModeVocab);

            VocabularyDType aPatternVocab = xcriFactory.createVocabularyDType();
            aPatternVocab.setIdentifier(dbPres.getAttendancePatternIdentifier());
            aPatternVocab.setContent(dbPres.getAttendancePattern());
            pdt.setAttendancePattern(aPatternVocab);

            pdt.setCost(xcb.createSimpleLiteral(dbPres.getCost()));

//            DurationDType ddType = mloFactory.createDurationDType();
//            ddType.setInterval(dbPres.getDuration());
//            
            
            pdt.setDuration(dbPres.getDuration());

            if (dbPres.getPend() != null) {
                TemporalDType tEnd = xcriFactory.createTemporalDType();
                tEnd.setContent(xcb.getXMLDate(dbPres.getPend()).toString());
                pdt.setEnd(tEnd);
            }
            if (dbPres.getEndFrom() != null) {
                TemporalDType tEndFrom = xcriFactory.createTemporalDType();
                tEndFrom.setContent(xcb.getXMLDate(dbPres.getEndFrom()).toString());
                pdt.setEndFrom(tEndFrom);
            }

            if (dbPres.getImageUrl() != null) {
                ImageDType iDType = xcriFactory.createImageDType();
                iDType.setAlt(dbPres.getImageAlt());
                iDType.setTitle(dbPres.getImageTitle());
                iDType.setSrc(dbPres.getImageUrl());
                pdt.setImage(iDType);
            }

            Resource placesResource = new Resource();
            for (XcriPresentationPlaces places : dbPres.getXcriPresentationPlacesList()) {
                placesResource.getContent().add(places.getPlace());
            }
            pdt.setPlaces(placesResource);

            StartDType start = mloFactory.createStartDType();
            pdt.setStart(start);
            if (dbPres.getPstart() != null) {
                start.setDtf(xcb.getXMLDate(dbPres.getPstart()).toString());
            }

            TemporalDType startUntil = xcriFactory.createTemporalDType();
            if (dbPres.getStartUntil() != null) {
                startUntil.setContent(xcb.getXMLDate(dbPres.getStartUntil()).toString());
            }
            pdt.setStartUntil(startUntil);
            VocabularyDType studyModeVocab = xcriFactory.createVocabularyDType();
            studyModeVocab.setIdentifier(dbPres.getStudyModeIdentifier());
            studyModeVocab.setContent(dbPres.getStudyMode());
            
            pdt.setStudyMode(studyModeVocab);
            pdt.setUrl(xcb.createSimpleLiteral(dbPres.getApplyTo()));
            pdt.getVenue().addAll(loadPresentationVenues(dbPres));
            pdt.getLanguageOfAssessment().addAll(getLanguageOfAssessment(dbPres));
            pdt.getLanguageOfInstruction().addAll(getLanguageOfInstruction(dbPres));
            pdt.getEngagement().addAll(getPresentationEngagement(dbPres));

            SimpleLiteral idLiteral = xcb.createSimpleLiteral(dbPres.getIdentifier());
            pdt.getIdentifier().add(dcFactory.createIdentifier(idLiteral));
            presentations.add(pdt);
        }
        return presentations;
    }

    /**
     * Returns a list of language of Assessments
     * @param dbPres
     * @return 
     */
    private List<String> getLanguageOfAssessment(XcriPresentation dbPres) {
        List<String> langs = new ArrayList<String>();
        for (XcriPrsntLangOfAssess language : dbPres.getXcriPrsntLangOfAssessList()) {
            langs.add(language.getLanguageOfAssess());
        }
        return langs;
    }

    /**
     * Returns a simple literal list representation of a presentation engagement
     * @param dbPres
     * @return 
     */
    private List<SimpleLiteral> getPresentationEngagement(XcriPresentation dbPres) {
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        List<SimpleLiteral> eng = new ArrayList<SimpleLiteral>();
        for (XcriPresentationEngagement language : dbPres.getXcriPresentationEngagementList()) {
            eng.add(xcb.createSimpleLiteral(language.getEngagement()));
        }
        return eng;
    }

    /**
     * Returns a simple literal list representation of language instructions
     * @param dbPres
     * @return 
     */
    private List<SimpleLiteral> getLanguageOfInstruction(XcriPresentation dbPres) {
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        List<SimpleLiteral> langs = new ArrayList<SimpleLiteral>();
        for (XcriPrsntLangOfInstruction language : dbPres.getXcriPrsntLangOfInstructionList()) {
            langs.add(xcb.createSimpleLiteral(language.getLanguageOfInstruction()));
        }
        return langs;
    }

    /**
     * Returns a list of venues for a presentation
     * @param dbPres
     * @return 
     */
    private List<VenueDType> loadPresentationVenues(XcriPresentation dbPres) {
        List<VenueDType> venues = new ArrayList<VenueDType>();
        org.xcri.profiles._1_2.catalog.ObjectFactory xcriFactory = new org.xcri.profiles._1_2.catalog.ObjectFactory();
        for (XcriProvider venueProvider : dbPres.getXcriProviderList()) {
            VenueDType vdt = xcriFactory.createVenueDType();
            ProviderDType pdt = buildProvider(venueProvider);
            vdt.setProvider(pdt);
            venues.add(vdt);
        }
        return venues;
    }

    /**
     * Returns a list of identifiers for a course
     * @param dbCourse
     * @return 
     */
    private List<JAXBElement<SimpleLiteral>> loadCourseIdentifiers(XcriCourse dbCourse) {
        org.purl.dc.elements._1.ObjectFactory f = new org.purl.dc.elements._1.ObjectFactory();
        List<JAXBElement<SimpleLiteral>> identifiers = new ArrayList<JAXBElement<SimpleLiteral>>();
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        for (XcriCourseIdentifier identifier : dbCourse.getXcriCourseIdentifierList()) {
            identifiers.add(f.createIdentifier(xcb.createSimpleLiteral(identifier.getIdentifierText())));
        }
        return identifiers;
    }

    /**
     * Returns a list of subjects for a course
     * @param dbCourse
     * @return 
     */
    private List<JAXBElement<SubjectDType>> loadCourseSubjects(XcriCourse dbCourse) {
        org.purl.dc.elements._1.ObjectFactory f = new org.purl.dc.elements._1.ObjectFactory();
        List<JAXBElement<SubjectDType>> subjects = new ArrayList<JAXBElement<SubjectDType>>();
        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        for (XcriCapCourseSubject subject : dbCourse.getXcriCapCourseSubjectList()) {
            SubjectDType sdt = f.createSubjectDType();
            sdt.setContent(subject.getSubject());
            subjects.add(f.createSubject(sdt));
        }
        return subjects;
    }

    /*
     * Returns a course catalog
     */
    private Catalog loadXCRIDocument(XcriCatalog dbCat) {

        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        org.purl.dc.terms.ObjectFactory f = new org.purl.dc.terms.ObjectFactory();
        org.xcri.profiles._1_2.catalog.ObjectFactory xcriFactory = new org.xcri.profiles._1_2.catalog.ObjectFactory();
        org.purl.dc.elements._1.ObjectFactory elemFactory = new org.purl.dc.elements._1.ObjectFactory();
        Catalog cat = xcriFactory.createCatalog();


        org.purl.dc.elements._1.DescriptionDType catDesc = elemFactory.createDescriptionDType();
        catDesc.setHref(dbCat.getHrefURL());
        catDesc.setLang("en");
        cat.getDescription().add(elemFactory.createDescription(catDesc));

        for (XcriProvider dbProvider : dbCat.getXcriProviderList()) {
            ProviderDType provider = buildProvider(dbProvider);
            provider.getCourse().addAll(loadXCRICourses(dbProvider));
            cat.getProvider().add(provider);
        }
        cat.setGenerated(xcb.getXMLDate(new Date()));
        return cat;
    }

    /**
     * Creates the initial provider information
     * @param dbProvider
     * @return 
     */
    private ProviderDType buildProvider(XcriProvider dbProvider) {
        org.xcri.profiles._1_2.catalog.ObjectFactory xcriFactory = new org.xcri.profiles._1_2.catalog.ObjectFactory();
        org.purl.dc.elements._1.ObjectFactory f = new org.purl.dc.elements._1.ObjectFactory();

        org.purl.net.mlo.ObjectFactory mloFactory = new org.purl.net.mlo.ObjectFactory();

        XCRI_CAPBusiness xcb = new XCRI_CAPBusiness();
        ProviderDType provider = xcriFactory.createProviderDType();

        Ukprn ukprn = new Ukprn();
        ukprn.getContent().add(dbProvider.getUkprn());
        provider.getIdentifier().add(f.createIdentifier(ukprn));
        provider.getIdentifier().add(f.createIdentifier(xcb.createSimpleLiteral("http://www.sussex.ac.uk")));
        //provider.getIdentifier().
        provider.getTitle().add(f.createTitle(xcb.createSimpleLiteral(dbProvider.getName())));

        if (dbProvider.getLocationId() != null) {
            XcriLocation dbLocation = dbProvider.getLocationId();
            provider.setUrl(xcb.createSimpleLiteral(dbLocation.getUrl()));
            LocationDType ldt = mloFactory.createLocationDType();
            ldt.setEmail(dbLocation.getEmail());
            ldt.setFax(dbLocation.getFax());
            ldt.setPhone(dbLocation.getPhone());
            ldt.setPostcode(dbLocation.getPostcode());
            ldt.setStreet(dbLocation.getStreet());
            ldt.setTown(dbLocation.getTown());
            ldt.setUrl(xcb.createSimpleLiteral(dbLocation.getUrl()));

            for (XcriAddress address : dbLocation.getXcriAddressList()) {
                ldt.getAddress().add(address.getAddress());
            }

            provider.getLocation().add(ldt);
        }

        return provider;
    }

    /**
     * Loads the course catalog from the database
     * @return 
     */
    private XcriCatalog loadCatalog(String catalogType) {
        XcriCatalog catalog = null;

        List<XcriCatalog> mainCats = dbLoader.loadCatalogs(catalogType);
        if (mainCats != null && !mainCats.isEmpty()) {
            catalog = mainCats.get(0);
        }

        return catalog;
    }
}
