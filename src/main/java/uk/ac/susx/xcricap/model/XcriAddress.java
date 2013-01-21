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
@Table(name = "XCRI_ADDRESS", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriAddress.findAll", query = "SELECT x FROM XcriAddress x"),
    @NamedQuery(name = "XcriAddress.findByAddressId", query = "SELECT x FROM XcriAddress x WHERE x.addressId = :addressId"),
    @NamedQuery(name = "XcriAddress.findByAddress", query = "SELECT x FROM XcriAddress x WHERE x.address = :address")})
public class XcriAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_ADDRESS_SEQ")
    @SequenceGenerator(name = "XCRI_ADDRESS_SEQ", sequenceName = "XCRI_ADDRESS_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADDRESS_ID")
    private BigDecimal addressId;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriLocation locationId;

    public XcriAddress() {
    }

    public XcriAddress(BigDecimal addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getAddressId() {
        return addressId;
    }

    public void setAddressId(BigDecimal addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public XcriLocation getLocationId() {
        return locationId;
    }

    public void setLocationId(XcriLocation locationId) {
        this.locationId = locationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriAddress)) {
            return false;
        }
        XcriAddress other = (XcriAddress) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriAddress[ addressId=" + addressId + " ]";
    }
    
}
