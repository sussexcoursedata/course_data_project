/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rjb41
 */
@Entity
@Table(name = "XCRI_PRESENTATION", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriPresentation.findAll", query = "SELECT x FROM XcriPresentation x"),
    @NamedQuery(name = "XcriPresentation.findByPresentationId", query = "SELECT x FROM XcriPresentation x WHERE x.presentationId = :presentationId"),
    @NamedQuery(name = "XcriPresentation.findByPstart", query = "SELECT x FROM XcriPresentation x WHERE x.pstart = :pstart"),
    @NamedQuery(name = "XcriPresentation.findByPend", query = "SELECT x FROM XcriPresentation x WHERE x.pend = :pend"),
    @NamedQuery(name = "XcriPresentation.findByStartUntil", query = "SELECT x FROM XcriPresentation x WHERE x.startUntil = :startUntil"),
    @NamedQuery(name = "XcriPresentation.findByEndFrom", query = "SELECT x FROM XcriPresentation x WHERE x.endFrom = :endFrom"),
    @NamedQuery(name = "XcriPresentation.findByDuration", query = "SELECT x FROM XcriPresentation x WHERE x.duration = :duration"),
    @NamedQuery(name = "XcriPresentation.findByApplyFrom", query = "SELECT x FROM XcriPresentation x WHERE x.applyFrom = :applyFrom"),
    @NamedQuery(name = "XcriPresentation.findByApplyUntil", query = "SELECT x FROM XcriPresentation x WHERE x.applyUntil = :applyUntil"),
    @NamedQuery(name = "XcriPresentation.findByApplyTo", query = "SELECT x FROM XcriPresentation x WHERE x.applyTo = :applyTo"),
    @NamedQuery(name = "XcriPresentation.findByStudyMode", query = "SELECT x FROM XcriPresentation x WHERE x.studyMode = :studyMode"),
    @NamedQuery(name = "XcriPresentation.findByAttendanceMode", query = "SELECT x FROM XcriPresentation x WHERE x.attendanceMode = :attendanceMode"),
    @NamedQuery(name = "XcriPresentation.findByAttendancePattern", query = "SELECT x FROM XcriPresentation x WHERE x.attendancePattern = :attendancePattern"),
    @NamedQuery(name = "XcriPresentation.findByCost", query = "SELECT x FROM XcriPresentation x WHERE x.cost = :cost"),
    @NamedQuery(name = "XcriPresentation.findByAge", query = "SELECT x FROM XcriPresentation x WHERE x.age = :age"),
    @NamedQuery(name = "XcriPresentation.findByImageUrl", query = "SELECT x FROM XcriPresentation x WHERE x.imageUrl = :imageUrl"),
    @NamedQuery(name = "XcriPresentation.findByImageAlt", query = "SELECT x FROM XcriPresentation x WHERE x.imageAlt = :imageAlt"),
    @NamedQuery(name = "XcriPresentation.findByImageTitle", query = "SELECT x FROM XcriPresentation x WHERE x.imageTitle = :imageTitle")})
public class XcriPresentation implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PRESENTATION_SEQ")
    @SequenceGenerator(name = "XCRI_PRESENTATION_SEQ", sequenceName = "XCRI_PRESENTATION_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRESENTATION_ID")
    private BigDecimal presentationId;
    @Column(name = "PSTART")
    @Temporal(TemporalType.DATE)
    private Date pstart;
    @Column(name = "PEND")
    @Temporal(TemporalType.DATE)
    private Date pend;
    @Column(name = "START_UNTIL")
    @Temporal(TemporalType.DATE)
    private Date startUntil;
    @Column(name = "END_FROM")
    @Temporal(TemporalType.DATE)
    private Date endFrom;
    @Size(max = 100)
    @Column(name = "DURATION")
    private String duration;
    @Column(name = "APPLY_FROM")
    @Temporal(TemporalType.DATE)
    private Date applyFrom;
    @Column(name = "APPLY_UNTIL")
    @Temporal(TemporalType.DATE)
    private Date applyUntil;
    @Size(max = 100)
    @Column(name = "APPLY_TO")
    private String applyTo;
    @Size(max = 100)
    @Column(name = "STUDY_MODE")
    private String studyMode;
    @Size(max = 500)
    @Column(name = "IDENTIFIER")
    private String identifier;
    @Size(max = 20)
    @Column(name = "STUDY_MODE_IDENTIFIER")
    private String studyModeIdentifier;  
    @Size(max = 100)
    @Column(name = "ATTENDANCE_MODE")
    private String attendanceMode;
    @Size(max = 100)
    @Column(name = "ATTENDANCE_PATTERN")
    private String attendancePattern;
    @Size(max = 1000)
    @Column(name = "COST")
    private String cost;
    @Size(max = 100)
    @Column(name = "AGE")
    private String age;
    @Size(max = 500)
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Size(max = 100)
    @Column(name = "IMAGE_ALT")
    private String imageAlt;
    @Size(max = 100)
    @Column(name = "IMAGE_TITLE")
    private String imageTitle;
    
    @Column(name = "ATTENDANCE_MODE_IDENTIFIER")
    private String attendanceModeIdentifier;
    
    @Column(name = "ATTENDANCE_PATTERN_IDENTIFIER")
    private String attendancePatternIdentifier;
    
    @JoinTable(name = "XCRI_PRESENTATION_VENUE", joinColumns = {
        @JoinColumn(name = "PRESENTATION_ID", referencedColumnName = "PRESENTATION_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "PROVIDER_ID")})
    @ManyToMany
    private List<XcriProvider> xcriProviderList;
    @OneToMany(mappedBy = "presentationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriPresentationEngagement> xcriPresentationEngagementList;
    @OneToMany(mappedBy = "presentationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriPresentationPlaces> xcriPresentationPlacesList;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private XcriCourse courseId;
    @OneToMany(mappedBy = "presentationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriPrsntLangOfInstruction> xcriPrsntLangOfInstructionList;
    @OneToMany(mappedBy = "presentationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriPrsntLangOfAssess> xcriPrsntLangOfAssessList;

    public XcriPresentation() {
    }

    public XcriPresentation(BigDecimal presentationId) {
        this.presentationId = presentationId;
    }

    public BigDecimal getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(BigDecimal presentationId) {
        this.presentationId = presentationId;
    }

    public Date getPstart() {
        return pstart;
    }

    public void setPstart(Date pstart) {
        this.pstart = pstart;
    }

    public Date getPend() {
        return pend;
    }

    public void setPend(Date pend) {
        this.pend = pend;
    }

    public Date getStartUntil() {
        return startUntil;
    }

    public void setStartUntil(Date startUntil) {
        this.startUntil = startUntil;
    }

    public Date getEndFrom() {
        return endFrom;
    }

    public void setEndFrom(Date endFrom) {
        this.endFrom = endFrom;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getApplyFrom() {
        return applyFrom;
    }

    public void setApplyFrom(Date applyFrom) {
        this.applyFrom = applyFrom;
    }

    public Date getApplyUntil() {
        return applyUntil;
    }

    public void setApplyUntil(Date applyUntil) {
        this.applyUntil = applyUntil;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getAttendanceMode() {
        return attendanceMode;
    }

    public void setAttendanceMode(String attendanceMode) {
        this.attendanceMode = attendanceMode;
    }

    public String getAttendancePattern() {
        return attendancePattern;
    }

    public void setAttendancePattern(String attendancePattern) {
        this.attendancePattern = attendancePattern;
    }

    public String getCost() {
        return cost;
    }

    public String getStudyModeIdentifier() {
        return studyModeIdentifier;
    }

    public void setStudyModeIdentifier(String studyModeIdentifier) {
        this.studyModeIdentifier = studyModeIdentifier;
    }

        
    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String provider) {
        this.identifier = provider;
    }

    public String getAttendanceModeIdentifier() {
        return attendanceModeIdentifier;
    }

    public void setAttendanceModeIdentifier(String attendanceModeIdentifier) {
        this.attendanceModeIdentifier = attendanceModeIdentifier;
    }

    public String getAttendancePatternIdentifier() {
        return attendancePatternIdentifier;
    }

    public void setAttendancePatternIdentifier(String attendancePatternIdentifier) {
        this.attendancePatternIdentifier = attendancePatternIdentifier;
    }
                
    @XmlTransient
    public List<XcriProvider> getXcriProviderList() {
        return xcriProviderList;
    }

    public void setXcriProviderList(List<XcriProvider> xcriProviderList) {
        this.xcriProviderList = xcriProviderList;
    }

    @XmlTransient
    public List<XcriPresentationEngagement> getXcriPresentationEngagementList() {
        return xcriPresentationEngagementList;
    }

    public void setXcriPresentationEngagementList(List<XcriPresentationEngagement> xcriPresentationEngagementList) {
        this.xcriPresentationEngagementList = xcriPresentationEngagementList;
    }

    @XmlTransient
    public List<XcriPresentationPlaces> getXcriPresentationPlacesList() {
        return xcriPresentationPlacesList;
    }

    public void setXcriPresentationPlacesList(List<XcriPresentationPlaces> xcriPresentationPlacesList) {
        this.xcriPresentationPlacesList = xcriPresentationPlacesList;
    }

    public XcriCourse getCourseId() {
        return courseId;
    }

    public void setCourseId(XcriCourse courseId) {
        this.courseId = courseId;
    }

    @XmlTransient
    public List<XcriPrsntLangOfInstruction> getXcriPrsntLangOfInstructionList() {
        return xcriPrsntLangOfInstructionList;
    }

    public void setXcriPrsntLangOfInstructionList(List<XcriPrsntLangOfInstruction> xcriPrsntLangOfInstructionList) {
        this.xcriPrsntLangOfInstructionList = xcriPrsntLangOfInstructionList;
    }

    @XmlTransient
    public List<XcriPrsntLangOfAssess> getXcriPrsntLangOfAssessList() {
        return xcriPrsntLangOfAssessList;
    }

    public void setXcriPrsntLangOfAssessList(List<XcriPrsntLangOfAssess> xcriPrsntLangOfAssessList) {
        this.xcriPrsntLangOfAssessList = xcriPrsntLangOfAssessList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (presentationId != null ? presentationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriPresentation)) {
            return false;
        }
        XcriPresentation other = (XcriPresentation) object;
        if ((this.presentationId == null && other.presentationId != null) || (this.presentationId != null && !this.presentationId.equals(other.presentationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriPresentation[ presentationId=" + presentationId + " ]";
    }
    
}
