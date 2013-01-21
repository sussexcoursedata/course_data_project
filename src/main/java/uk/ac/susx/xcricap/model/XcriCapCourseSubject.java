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
@Table(name = "XCRI_CAP_COURSE_SUBJECT", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriCapCourseSubject.findAll", query = "SELECT x FROM XcriCapCourseSubject x"),
    @NamedQuery(name = "XcriCapCourseSubject.findBySubjectId", query = "SELECT x FROM XcriCapCourseSubject x WHERE x.subjectId = :subjectId"),
    @NamedQuery(name = "XcriCapCourseSubject.findBySubject", query = "SELECT x FROM XcriCapCourseSubject x WHERE x.subject = :subject")})
public class XcriCapCourseSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_COURSE_SUBJECT_SEQ")
    @SequenceGenerator(name = "XCRI_COURSE_SUBJECT_SEQ", sequenceName = "XCRI_COURSE_SUBJECT_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBJECT_ID")
    private BigDecimal subjectId;
    @Size(max = 100)
    @Column(name = "SUBJECT")
    private String subject;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriCourse courseId;

    public XcriCapCourseSubject() {
    }

    public XcriCapCourseSubject(BigDecimal subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(BigDecimal subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public XcriCourse getCourseId() {
        return courseId;
    }

    public void setCourseId(XcriCourse courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriCapCourseSubject)) {
            return false;
        }
        XcriCapCourseSubject other = (XcriCapCourseSubject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriCapCourseSubject[ subjectId=" + subjectId + " ]";
    }
    
}
