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
@Table(name = "XCRI_PRESENTATION_ENGAGEMENT", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriPresentationEngagement.findAll", query = "SELECT x FROM XcriPresentationEngagement x"),
    @NamedQuery(name = "XcriPresentationEngagement.findByPresentationEngagementId", query = "SELECT x FROM XcriPresentationEngagement x WHERE x.presentationEngagementId = :presentationEngagementId"),
    @NamedQuery(name = "XcriPresentationEngagement.findByEngagement", query = "SELECT x FROM XcriPresentationEngagement x WHERE x.engagement = :engagement")})
public class XcriPresentationEngagement implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PRESENTATION_ENGMNT_SEQ")
    @SequenceGenerator(name = "XCRI_PRESENTATION_ENGMNT_SEQ", sequenceName = "XCRI_PRESENTATION_ENGMNT_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRESENTATION_ENGAGEMENT_ID")
    private BigDecimal presentationEngagementId;
    @Size(max = 100)
    @Column(name = "ENGAGEMENT")
    private String engagement;
    @JoinColumn(name = "PRESENTATION_ID", referencedColumnName = "PRESENTATION_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriPresentation presentationId;

    public XcriPresentationEngagement() {
    }

    public XcriPresentationEngagement(BigDecimal presentationEngagementId) {
        this.presentationEngagementId = presentationEngagementId;
    }

    public BigDecimal getPresentationEngagementId() {
        return presentationEngagementId;
    }

    public void setPresentationEngagementId(BigDecimal presentationEngagementId) {
        this.presentationEngagementId = presentationEngagementId;
    }

    public String getEngagement() {
        return engagement;
    }

    public void setEngagement(String engagement) {
        this.engagement = engagement;
    }

    public XcriPresentation getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(XcriPresentation presentationId) {
        this.presentationId = presentationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (presentationEngagementId != null ? presentationEngagementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriPresentationEngagement)) {
            return false;
        }
        XcriPresentationEngagement other = (XcriPresentationEngagement) object;
        if ((this.presentationEngagementId == null && other.presentationEngagementId != null) || (this.presentationEngagementId != null && !this.presentationEngagementId.equals(other.presentationEngagementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriPresentationEngagement[ presentationEngagementId=" + presentationEngagementId + " ]";
    }
    
}
