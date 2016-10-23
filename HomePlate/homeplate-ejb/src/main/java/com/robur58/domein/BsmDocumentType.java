package com.robur58.domein;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BSM_DOCUMENT_TYPE")
@NamedQueries({
    @NamedQuery(name = "BsmDocumentType.findAll", query = "SELECT b FROM BsmDocumentType b"),
    @NamedQuery(name = "BsmDocumentType.findByDceVolgnr", query = "SELECT b FROM BsmDocumentType b WHERE b.dceVolgnr = :dceVolgnr"),
    @NamedQuery(name = "BsmDocumentType.findByOmschrijving", query = "SELECT b FROM BsmDocumentType b WHERE b.omschrijving = :omschrijving")})
public class BsmDocumentType implements Serializable {
    @JoinTable(name = "BSM_DOC_DCE", joinColumns = {
        @JoinColumn(name = "DCE_VOLGNR", referencedColumnName = "DCE_VOLGNR")}, inverseJoinColumns = {
        @JoinColumn(name = "DOC_VOLGNR", referencedColumnName = "DCT_VOLGNR")})
    @ManyToMany
    private Collection<BsmDocInfo> bsmDocInfoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DocumentTypeSequence")
    @SequenceGenerator(name="DocumentTypeSequence", sequenceName="DCE_SEQ", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "DCE_VOLGNR")
    private Long dceVolgnr;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Column(name = "FOTOALBUM")
    private String fotoalbum;

    public BsmDocumentType() {
    }

    public BsmDocumentType(Long dceVolgnr) {
        this.dceVolgnr = dceVolgnr;
    }

    public Long getDceVolgnr() {
        return dceVolgnr;
    }

    public void setDceVolgnr(Long dceVolgnr) {
        this.dceVolgnr = dceVolgnr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getFotoalbum() {
        return fotoalbum;
    }

    public void setFotoalbum(String fotoalbum) {
        this.fotoalbum = fotoalbum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dceVolgnr != null ? dceVolgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmDocumentType)) {
            return false;
        }
        BsmDocumentType other = (BsmDocumentType) object;
        if ((this.dceVolgnr == null && other.dceVolgnr != null) || (this.dceVolgnr != null && !this.dceVolgnr.equals(other.dceVolgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return omschrijving;
    }

    public Collection<BsmDocInfo> getBsmDocInfoCollection() {
        return bsmDocInfoCollection;
    }

    public void setBsmDocInfoCollection(Collection<BsmDocInfo> bsmDocInfoCollection) {
        this.bsmDocInfoCollection = bsmDocInfoCollection;
    }

}
