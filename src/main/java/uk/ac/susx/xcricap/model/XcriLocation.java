/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "XCRI_LOCATION", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriLocation.findAll", query = "SELECT x FROM XcriLocation x"),
    @NamedQuery(name = "XcriLocation.findByLocationId", query = "SELECT x FROM XcriLocation x WHERE x.locationId = :locationId"),
    @NamedQuery(name = "XcriLocation.findByStreet", query = "SELECT x FROM XcriLocation x WHERE x.street = :street"),
    @NamedQuery(name = "XcriLocation.findByTown", query = "SELECT x FROM XcriLocation x WHERE x.town = :town"),
    @NamedQuery(name = "XcriLocation.findByPostcode", query = "SELECT x FROM XcriLocation x WHERE x.postcode = :postcode"),
    @NamedQuery(name = "XcriLocation.findByPhone", query = "SELECT x FROM XcriLocation x WHERE x.phone = :phone"),
    @NamedQuery(name = "XcriLocation.findByFax", query = "SELECT x FROM XcriLocation x WHERE x.fax = :fax"),
    @NamedQuery(name = "XcriLocation.findByEmail", query = "SELECT x FROM XcriLocation x WHERE x.email = :email"),
    @NamedQuery(name = "XcriLocation.findByUrl", query = "SELECT x FROM XcriLocation x WHERE x.url = :url")})
public class XcriLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_LOCATION_SEQ")
    @SequenceGenerator(name = "XCRI_LOCATION_SEQ", sequenceName = "XCRI_LOCATION_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION_ID")
    private BigDecimal locationId;
    @Size(max = 100)
    @Column(name = "STREET")
    private String street;
    @Size(max = 100)
    @Column(name = "TOWN")
    private String town;
    @Size(max = 10)
    @Column(name = "POSTCODE")
    private String postcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "FAX")
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 500)
    @Column(name = "URL")
    private String url;
    @OneToMany(mappedBy = "locationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriAddress> xcriAddressList;
    @OneToMany(mappedBy = "locationId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriProvider> xcriProviderList;

    public XcriLocation() {
    }

    public XcriLocation(BigDecimal locationId) {
        this.locationId = locationId;
    }

    public BigDecimal getLocationId() {
        return locationId;
    }

    public void setLocationId(BigDecimal locationId) {
        this.locationId = locationId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<XcriAddress> getXcriAddressList() {
        return xcriAddressList;
    }

    public void setXcriAddressList(List<XcriAddress> xcriAddressList) {
        this.xcriAddressList = xcriAddressList;
    }

    @XmlTransient
    public List<XcriProvider> getXcriProviderList() {
        return xcriProviderList;
    }

    public void setXcriProviderList(List<XcriProvider> xcriProviderList) {
        this.xcriProviderList = xcriProviderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriLocation)) {
            return false;
        }
        XcriLocation other = (XcriLocation) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriLocation[ locationId=" + locationId + " ]";
    }
    
}
