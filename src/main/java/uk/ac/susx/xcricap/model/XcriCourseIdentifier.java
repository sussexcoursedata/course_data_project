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
@Table(name = "XCRI_COURSE_IDENTIFIER", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriCourseIdentifier.findAll", query = "SELECT x FROM XcriCourseIdentifier x"),
    @NamedQuery(name = "XcriCourseIdentifier.findByIdentifierId", query = "SELECT x FROM XcriCourseIdentifier x WHERE x.identifierId = :identifierId"),
    @NamedQuery(name = "XcriCourseIdentifier.findByIdentifierText", query = "SELECT x FROM XcriCourseIdentifier x WHERE x.identifierText = :identifierText")})
public class XcriCourseIdentifier implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_COURSE_IDENTIFIER_SEQ")
    @SequenceGenerator(name = "XCRI_COURSE_IDENTIFIER_SEQ", sequenceName = "XCRI_COURSE_IDENTIFIER_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDENTIFIER_ID")
    private BigDecimal identifierId;
    @Size(max = 100)
    @Column(name = "IDENTIFIER_TEXT")
    private String identifierText;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriCourse courseId;

    public XcriCourseIdentifier() {
    }

    public XcriCourseIdentifier(BigDecimal identifierId) {
        this.identifierId = identifierId;
    }

    public BigDecimal getIdentifierId() {
        return identifierId;
    }

    public void setIdentifierId(BigDecimal identifierId) {
        this.identifierId = identifierId;
    }

    public String getIdentifierText() {
        return identifierText;
    }

    public void setIdentifierText(String identifierText) {
        this.identifierText = identifierText;
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
        hash += (identifierId != null ? identifierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriCourseIdentifier)) {
            return false;
        }
        XcriCourseIdentifier other = (XcriCourseIdentifier) object;
        if ((this.identifierId == null && other.identifierId != null) || (this.identifierId != null && !this.identifierId.equals(other.identifierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriCourseIdentifier[ identifierId=" + identifierId + " ]";
    }
    
}
