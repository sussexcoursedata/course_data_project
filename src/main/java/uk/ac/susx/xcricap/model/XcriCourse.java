/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rjb41
 */
@Entity
@Table(name = "XCRI_COURSE", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriCourse.findAll", query = "SELECT x FROM XcriCourse x"),
    @NamedQuery(name = "XcriCourse.findByCourseId", query = "SELECT x FROM XcriCourse x WHERE x.courseId = :courseId"),
    @NamedQuery(name = "XcriCourse.findByTitle", query = "SELECT x FROM XcriCourse x WHERE x.title = :title"),
    @NamedQuery(name = "XcriCourse.findByUrl", query = "SELECT x FROM XcriCourse x WHERE x.url = :url")})
public class XcriCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_COURSE_SEQ")
    @SequenceGenerator(name = "XCRI_COURSE_SEQ", sequenceName = "XCRI_COURSE_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_ID")
    private BigDecimal courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 500)
    @Column(name = "URL")
    private String url;
    
    @Size(max = 500)
    @Column(name = "APPLICATION_PROCEDURE")
    private String applicationProcedure;
    
    @Column(name = "ABSTRACT")
    private String c_abstract;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "PROVIDER_ID", referencedColumnName = "PROVIDER_ID")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private XcriProvider providerId;
    @OneToMany(mappedBy = "courseId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriCourseIdentifier> xcriCourseIdentifierList;
    @OneToMany(mappedBy = "courseId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriCredit> xcriCreditList;
    @OneToMany(mappedBy = "courseId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriCapCourseSubject> xcriCapCourseSubjectList;
    @OneToMany(mappedBy = "courseId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriPresentation> xcriPresentationList;
    @OneToMany(mappedBy = "courseId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriQualification> xcriQualificationList;

    public XcriCourse() {
    }

    public XcriCourse(BigDecimal courseId) {
        this.courseId = courseId;
    }

    public XcriCourse(BigDecimal courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }

    public String getC_abstract() {
        return c_abstract;
    }

    public void setC_abstract(String c_abstract) {
        this.c_abstract = c_abstract;
    }
    
    public BigDecimal getCourseId() {
        return courseId;
    }

    public void setCourseId(BigDecimal courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public XcriProvider getProviderId() {
        return providerId;
    }

    public void setProviderId(XcriProvider providerId) {
        this.providerId = providerId;
    }

    public String getApplicationProcedure() {
        return applicationProcedure;
    }

    public void setApplicationProcedure(String applicationProcedure) {
        this.applicationProcedure = applicationProcedure;
    }
       

    @XmlTransient
    public List<XcriCourseIdentifier> getXcriCourseIdentifierList() {
        return xcriCourseIdentifierList;
    }

    public void setXcriCourseIdentifierList(List<XcriCourseIdentifier> xcriCourseIdentifierList) {
        this.xcriCourseIdentifierList = xcriCourseIdentifierList;
    }

    @XmlTransient
    public List<XcriCredit> getXcriCreditList() {
        return xcriCreditList;
    }

    public void setXcriCreditList(List<XcriCredit> xcriCreditList) {
        this.xcriCreditList = xcriCreditList;
    }

    @XmlTransient
    public List<XcriCapCourseSubject> getXcriCapCourseSubjectList() {
        return xcriCapCourseSubjectList;
    }

    public void setXcriCapCourseSubjectList(List<XcriCapCourseSubject> xcriCapCourseSubjectList) {
        this.xcriCapCourseSubjectList = xcriCapCourseSubjectList;
    }

    @XmlTransient
    public List<XcriPresentation> getXcriPresentationList() {
        return xcriPresentationList;
    }

    public void setXcriPresentationList(List<XcriPresentation> xcriPresentationList) {
        this.xcriPresentationList = xcriPresentationList;
    }

    @XmlTransient
    public List<XcriQualification> getXcriQualificationList() {
        return xcriQualificationList;
    }

    public void setXcriQualificationList(List<XcriQualification> xcriQualificationList) {
        this.xcriQualificationList = xcriQualificationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriCourse)) {
            return false;
        }
        XcriCourse other = (XcriCourse) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriCourse[ courseId=" + courseId + " ]";
    }
    
}
