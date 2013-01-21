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
@Table(name = "XCRI_PRESENTATION_PLACES", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriPresentationPlaces.findAll", query = "SELECT x FROM XcriPresentationPlaces x"),
    @NamedQuery(name = "XcriPresentationPlaces.findByPresentationPlaceId", query = "SELECT x FROM XcriPresentationPlaces x WHERE x.presentationPlaceId = :presentationPlaceId"),
    @NamedQuery(name = "XcriPresentationPlaces.findByPlace", query = "SELECT x FROM XcriPresentationPlaces x WHERE x.place = :place")})
public class XcriPresentationPlaces implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PRESENTATION_PLACES_SEQ")
    @SequenceGenerator(name = "XCRI_PRESENTATION_PLACES_SEQ", sequenceName = "XCRI_PRESENTATION_PLACES_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRESENTATION_PLACE_ID")
    private BigDecimal presentationPlaceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PLACE")
    private String place;
    @JoinColumn(name = "PRESENTATION_ID", referencedColumnName = "PRESENTATION_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriPresentation presentationId;

    public XcriPresentationPlaces() {
    }

    public XcriPresentationPlaces(BigDecimal presentationPlaceId) {
        this.presentationPlaceId = presentationPlaceId;
    }

    public XcriPresentationPlaces(BigDecimal presentationPlaceId, String place) {
        this.presentationPlaceId = presentationPlaceId;
        this.place = place;
    }

    public BigDecimal getPresentationPlaceId() {
        return presentationPlaceId;
    }

    public void setPresentationPlaceId(BigDecimal presentationPlaceId) {
        this.presentationPlaceId = presentationPlaceId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
        hash += (presentationPlaceId != null ? presentationPlaceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriPresentationPlaces)) {
            return false;
        }
        XcriPresentationPlaces other = (XcriPresentationPlaces) object;
        if ((this.presentationPlaceId == null && other.presentationPlaceId != null) || (this.presentationPlaceId != null && !this.presentationPlaceId.equals(other.presentationPlaceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriPresentationPlaces[ presentationPlaceId=" + presentationPlaceId + " ]";
    }
    
}
