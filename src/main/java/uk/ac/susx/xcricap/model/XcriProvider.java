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
import javax.persistence.ManyToMany;
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
@Table(name = "XCRI_PROVIDER", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriProvider.findAll", query = "SELECT x FROM XcriProvider x"),
    @NamedQuery(name = "XcriProvider.findByProviderId", query = "SELECT x FROM XcriProvider x WHERE x.providerId = :providerId"),
    @NamedQuery(name = "XcriProvider.findByIdentifier", query = "SELECT x FROM XcriProvider x WHERE x.identifier = :identifier"),
    @NamedQuery(name = "XcriProvider.findByName", query = "SELECT x FROM XcriProvider x WHERE x.name = :name")})
public class XcriProvider implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PROVIDER_SEQ")
    @SequenceGenerator(name = "XCRI_PROVIDER_SEQ", sequenceName = "XCRI_PROVIDER_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROVIDER_ID")
    private BigDecimal providerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IDENTIFIER")
    private String identifier;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Column(name = "UKPRN")
    private String ukprn;
    @ManyToMany(mappedBy = "xcriProviderList")
    private List<XcriPresentation> xcriPresentationList;
    @OneToMany(mappedBy = "providerId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriCourse> xcriCourseList;
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID" )
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriLocation locationId;
    @JoinColumn(name = "CATALOG_ID", referencedColumnName = "CATALOG_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriCatalog catalogId;
    
    public XcriProvider() {
    }

    public XcriProvider(BigDecimal providerId) {
        this.providerId = providerId;
    }

    public XcriProvider(BigDecimal providerId, String identifier) {
        this.providerId = providerId;
        this.identifier = identifier;
    }

    public BigDecimal getProviderId() {
        return providerId;
    }

    public void setProviderId(BigDecimal providerId) {
        this.providerId = providerId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUkprn() {
        return ukprn;
    }

    public void setUkprn(String ukprn) {
        this.ukprn = ukprn;
    }
        

    @XmlTransient
    public List<XcriPresentation> getXcriPresentationList() {
        return xcriPresentationList;
    }

    public void setXcriPresentationList(List<XcriPresentation> xcriPresentationList) {
        this.xcriPresentationList = xcriPresentationList;
    }

    @XmlTransient
    public List<XcriCourse> getXcriCourseList() {
        return xcriCourseList;
    }

    public void setXcriCourseList(List<XcriCourse> xcriCourseList) {
        this.xcriCourseList = xcriCourseList;
    }

    public XcriLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(XcriLocation locationId) {
        this.locationId = locationId;
    }

    public XcriCatalog getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(XcriCatalog catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (providerId != null ? providerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriProvider)) {
            return false;
        }
        XcriProvider other = (XcriProvider) object;
        if ((this.providerId == null && other.providerId != null) || (this.providerId != null && !this.providerId.equals(other.providerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriProvider[ providerId=" + providerId + " ]";
    }
    
}
