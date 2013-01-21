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
@Table(name = "XCRI_CREDIT", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriCredit.findAll", query = "SELECT x FROM XcriCredit x"),
    @NamedQuery(name = "XcriCredit.findByCreditId", query = "SELECT x FROM XcriCredit x WHERE x.creditId = :creditId"),
    @NamedQuery(name = "XcriCredit.findByScheme", query = "SELECT x FROM XcriCredit x WHERE x.scheme = :scheme"),
    @NamedQuery(name = "XcriCredit.findByCreditLevel", query = "SELECT x FROM XcriCredit x WHERE x.creditLevel = :creditLevel"),
    @NamedQuery(name = "XcriCredit.findByCreditValue", query = "SELECT x FROM XcriCredit x WHERE x.creditValue = :creditValue")})
public class XcriCredit implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_CREDIT_SEQ")
    @SequenceGenerator(name = "XCRI_CREDIT_SEQ", sequenceName = "XCRI_CREDIT_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDIT_ID")
    private BigDecimal creditId;
    @Size(max = 100)
    @Column(name = "SCHEME")
    private String scheme;
    @Size(max = 100)
    @Column(name = "CREDIT_LEVEL")
    private String creditLevel;
    @Size(max = 100)
    @Column(name = "CREDIT_VALUE")
    private String creditValue;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriCourse courseId;

    public XcriCredit() {
    }

    public XcriCredit(BigDecimal creditId) {
        this.creditId = creditId;
    }

    public BigDecimal getCreditId() {
        return creditId;
    }

    public void setCreditId(BigDecimal creditId) {
        this.creditId = creditId;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public String getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(String creditValue) {
        this.creditValue = creditValue;
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
        hash += (creditId != null ? creditId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriCredit)) {
            return false;
        }
        XcriCredit other = (XcriCredit) object;
        if ((this.creditId == null && other.creditId != null) || (this.creditId != null && !this.creditId.equals(other.creditId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriCredit[ creditId=" + creditId + " ]";
    }
    
}
