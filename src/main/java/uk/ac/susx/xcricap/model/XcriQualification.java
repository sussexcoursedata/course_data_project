/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rjb41
 */
@Entity
@Table(name = "XCRI_QUALIFICATION", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriQualification.findAll", query = "SELECT x FROM XcriQualification x"),
    @NamedQuery(name = "XcriQualification.findByQualificationId", query = "SELECT x FROM XcriQualification x WHERE x.qualificationId = :qualificationId"),
    @NamedQuery(name = "XcriQualification.findByIdentifier", query = "SELECT x FROM XcriQualification x WHERE x.identifier = :identifier"),
    @NamedQuery(name = "XcriQualification.findByTitle", query = "SELECT x FROM XcriQualification x WHERE x.title = :title"),
    @NamedQuery(name = "XcriQualification.findByEducationLevel", query = "SELECT x FROM XcriQualification x WHERE x.educationLevel = :educationLevel"),
    @NamedQuery(name = "XcriQualification.findByType", query = "SELECT x FROM XcriQualification x WHERE x.type = :type"),
    @NamedQuery(name = "XcriQualification.findByUrl", query = "SELECT x FROM XcriQualification x WHERE x.url = :url"),
    @NamedQuery(name = "XcriQualification.findByAwardedBy", query = "SELECT x FROM XcriQualification x WHERE x.awardedBy = :awardedBy"),
    @NamedQuery(name = "XcriQualification.findByAccreditedBy", query = "SELECT x FROM XcriQualification x WHERE x.accreditedBy = :accreditedBy")})
public class XcriQualification implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_QUALIFICATION_SEQ")
    @SequenceGenerator(name = "XCRI_QUALIFICATION_SEQ", sequenceName = "XCRI_QUALIFICATION_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUALIFICATION_ID")
    private BigDecimal qualificationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IDENTIFIER")
    private String identifier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 10)
    @Column(name = "EDUCATION_LEVEL")
    private String educationLevel;
    @Size(max = 10)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 500)
    @Column(name = "URL")
    private String url;
    @Size(max = 100)
    @Column(name = "AWARDED_BY")
    private String awardedBy;
    @Size(max = 100)
    @Column(name = "ACCREDITED_BY")
    private String accreditedBy;
    @Column(name = "ABBREVIATION")
    private String abbreviation;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriCourse courseId;

    public XcriQualification() {
    }

    public XcriQualification(BigDecimal qualificationId) {
        this.qualificationId = qualificationId;
    }

    public XcriQualification(BigDecimal qualificationId, String identifier, String title) {
        this.qualificationId = qualificationId;
        this.identifier = identifier;
        this.title = title;
    }

    public BigDecimal getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(BigDecimal qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAwardedBy() {
        return awardedBy;
    }

    public void setAwardedBy(String awardedBy) {
        this.awardedBy = awardedBy;
    }

    public String getAccreditedBy() {
        return accreditedBy;
    }

    public void setAccreditedBy(String accreditedBy) {
        this.accreditedBy = accreditedBy;
    }

    public XcriCourse getCourseId() {
        return courseId;
    }

    public void setCourseId(XcriCourse courseId) {
        this.courseId = courseId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qualificationId != null ? qualificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriQualification)) {
            return false;
        }
        XcriQualification other = (XcriQualification) object;
        if ((this.qualificationId == null && other.qualificationId != null) || (this.qualificationId != null && !this.qualificationId.equals(other.qualificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriQualification[ qualificationId=" + qualificationId + " ]";
    }
    
}
