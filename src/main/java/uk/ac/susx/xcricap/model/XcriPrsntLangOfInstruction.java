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
@Table(name = "XCRI_PRSNT_LANG_OF_INSTRUCTION", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XcriPrsntLangOfInstruction.findAll", query = "SELECT x FROM XcriPrsntLangOfInstruction x"),
    @NamedQuery(name = "XcriPrsntLangOfInstruction.findByLangOfInstrId", query = "SELECT x FROM XcriPrsntLangOfInstruction x WHERE x.langOfInstrId = :langOfInstrId"),
    @NamedQuery(name = "XcriPrsntLangOfInstruction.findByLanguageOfInstruction", query = "SELECT x FROM XcriPrsntLangOfInstruction x WHERE x.languageOfInstruction = :languageOfInstruction")})
public class XcriPrsntLangOfInstruction implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "XCRI_PRSNT_LANG_OF_INSTR_SEQ")
    @SequenceGenerator(name = "XCRI_PRSNT_LANG_OF_INSTR_SEQ", sequenceName = "XCRI_PRSNT_LANG_OF_INSTR_SEQ", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LANG_OF_INSTR_ID")
    private BigDecimal langOfInstrId;
    @Size(max = 100)
    @Column(name = "LANGUAGE_OF_INSTRUCTION")
    private String languageOfInstruction;
    @JoinColumn(name = "PRESENTATION_ID", referencedColumnName = "PRESENTATION_ID")
    @ManyToOne(cascade=CascadeType.ALL)
    private XcriPresentation presentationId;

    public XcriPrsntLangOfInstruction() {
    }

    public XcriPrsntLangOfInstruction(BigDecimal langOfInstrId) {
        this.langOfInstrId = langOfInstrId;
    }

    public BigDecimal getLangOfInstrId() {
        return langOfInstrId;
    }

    public void setLangOfInstrId(BigDecimal langOfInstrId) {
        this.langOfInstrId = langOfInstrId;
    }

    public String getLanguageOfInstruction() {
        return languageOfInstruction;
    }

    public void setLanguageOfInstruction(String languageOfInstruction) {
        this.languageOfInstruction = languageOfInstruction;
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
        hash += (langOfInstrId != null ? langOfInstrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XcriPrsntLangOfInstruction)) {
            return false;
        }
        XcriPrsntLangOfInstruction other = (XcriPrsntLangOfInstruction) object;
        if ((this.langOfInstrId == null && other.langOfInstrId != null) || (this.langOfInstrId != null && !this.langOfInstrId.equals(other.langOfInstrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.ac.susx.xcricap.model.XcriPrsntLangOfInstruction[ langOfInstrId=" + langOfInstrId + " ]";
    }
    
}
