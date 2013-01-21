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
@Table(name = "XCRI_PRSNT_LANG_OF_ASSESS", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriPrsntLangOfAssess.findAll", query = "SELECT x FROM XcriPrsntLangOfAssess x"),
    @NamedQuery(name = "XcriPrsntLangOfAssess.findByLangOfAssessId", query = "SELECT x FROM XcriPrsntLangOfAssess x WHERE x.langOfAssessId = :langOfAssessId"),
    @NamedQuery(name = "XcriPrsntLangOfAssess.findByLanguageOfAssess", query = "SELECT x FROM XcriPrsntLangOfAssess x WHERE x.languageOfAssess = :languageOfAssess")})
public class XcriPrsntLangOfAssess implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PRSNT_LANG_OF_ASSESS_SEQ")
    @SequenceGenerator(name = "XCRI_PRSNT_LANG_OF_ASSESS_SEQ", sequenceName = "XCRI_PRSNT_LANG_OF_ASSESS_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LANG_OF_ASSESS_ID")
    private BigDecimal langOfAssessId;
    @Size(max = 100)
    @Column(name = "LANGUAGE_OF_ASSESS")
    private String languageOfAssess;
    @JoinColumn(name = "PRESENTATION_ID", referencedColumnName = "PRESENTATION_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriPresentation presentationId;

    public XcriPrsntLangOfAssess() {
    }

    public XcriPrsntLangOfAssess(BigDecimal langOfAssessId) {
        this.langOfAssessId = langOfAssessId;
    }

    public BigDecimal getLangOfAssessId() {
        return langOfAssessId;
    }

    public void setLangOfAssessId(BigDecimal langOfAssessId) {
        this.langOfAssessId = langOfAssessId;
    }

    public String getLanguageOfAssess() {
        return languageOfAssess;
    }

    public void setLanguageOfAssess(String languageOfAssess) {
        this.languageOfAssess = languageOfAssess;
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
        hash += (langOfAssessId != null ? langOfAssessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriPrsntLangOfAssess)) {
            return false;
        }
        XcriPrsntLangOfAssess other = (XcriPrsntLangOfAssess) object;
        if ((this.langOfAssessId == null && other.langOfAssessId != null) || (this.langOfAssessId != null && !this.langOfAssessId.equals(other.langOfAssessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriPrsntLangOfAssess[ langOfAssessId=" + langOfAssessId + " ]";
    }
    
}
