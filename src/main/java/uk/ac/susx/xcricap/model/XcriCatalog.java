/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.susx.xcricap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rjb41
 */
@Entity
@Table(name = "XCRI_CATALOG", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriCatalog.findAll", query = "SELECT x FROM XcriCatalog x"),
    @NamedQuery(name = "XcriCatalog.findByCatalogId", query = "SELECT x FROM XcriCatalog x WHERE x.catalogId = :catalogId"),
    @NamedQuery(name = "XcriCatalog.findByCatalogTitle", query = "SELECT x FROM XcriCatalog x WHERE x.catalogTitle = :catalogTitle"),
    @NamedQuery(name = "XcriCatalog.findByCatalogType", query = "SELECT x FROM XcriCatalog x WHERE x.catalogType = :catalogType"),
    @NamedQuery(name = "XcriCatalog.findByCreatedDate", query = "SELECT x FROM XcriCatalog x WHERE x.createdDate = :createdDate")})
public class XcriCatalog implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_CATALOG_SEQ")
    @SequenceGenerator(name = "XCRI_CATALOG_SEQ", sequenceName = "XCRI_CATALOG_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATALOG_ID")
    private BigDecimal catalogId;
    @Size(max = 100)
    @Column(name = "CATALOG_TITLE")
    private String catalogTitle;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "CATALOG_TYPE")
    private String catalogType;
    
    @Column(name = "HREF_URL")
    private String hrefURL;
    
    @OneToMany(mappedBy = "catalogId", cascade={javax.persistence.CascadeType.ALL})
    private List<XcriProvider> xcriProviderList;

    public XcriCatalog() {
    }

    public XcriCatalog(BigDecimal catalogId) {
        this.catalogId = catalogId;
    }

    public BigDecimal getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(BigDecimal catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogTitle() {
        return catalogTitle;
    }

    public void setCatalogTitle(String catalogTitle) {
        this.catalogTitle = catalogTitle;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getHrefURL() {
        return hrefURL;
    }

    public void setHrefURL(String hrefURL) {
        this.hrefURL = hrefURL;
    }

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
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
        hash += (catalogId != null ? catalogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriCatalog)) {
            return false;
        }
        XcriCatalog other = (XcriCatalog) object;
        if ((this.catalogId == null && other.catalogId != null) || (this.catalogId != null && !this.catalogId.equals(other.catalogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriCatalog[ catalogId=" + catalogId + " ]";
    }
    
}
