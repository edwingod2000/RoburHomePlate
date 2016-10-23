package com.robur58.domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "BSM_DOC_INFO")
@NamedQueries({
    @NamedQuery(name = "BsmDocInfo.findAll", query = "SELECT b FROM BsmDocInfo b"),
    @NamedQuery(name = "BsmDocInfo.findByDctVolgnr", query = "SELECT b FROM BsmDocInfo b WHERE b.dctVolgnr = :dctVolgnr"),
    @NamedQuery(name = "BsmDocInfo.findByNaam", query = "SELECT b FROM BsmDocInfo b WHERE b.naam = :naam"),
    @NamedQuery(name = "BsmDocInfo.findByGrootte", query = "SELECT b FROM BsmDocInfo b WHERE b.grootte = :grootte"),
    @NamedQuery(name = "BsmDocInfo.findByDatum", query = "SELECT b FROM BsmDocInfo b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmDocInfo.findByDatumOpname", query = "SELECT b FROM BsmDocInfo b WHERE b.datumOpname = :datumOpname"),
    @NamedQuery(name = "BsmDocInfo.findByDatumGeraadpleegd", query = "SELECT b FROM BsmDocInfo b WHERE b.datumGeraadpleegd = :datumGeraadpleegd"),
    @NamedQuery(name = "BsmDocInfo.findByInfo", query = "SELECT b FROM BsmDocInfo b WHERE b.info = :info")})
public class BsmDocInfo implements Serializable {
    @ManyToMany(mappedBy = "bsmDocInfoCollection")
    private List<BsmDocumentType> bsmDocumentTypeCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DCT_VOLGNR")
    private Long dctVolgnr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "NAAM")
    private String naam;
    @Column(name = "GROOTTE")
    private Long grootte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "DATUM_OPNAME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOpname;
    @Column(name = "DATUM_GERAADPLEEGD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumGeraadpleegd;
    @Size(max = 4000)
    @Column(name = "INFO")
    private String info;

    public BsmDocInfo() {
    }

    public BsmDocInfo(Long dctVolgnr) {
        this.dctVolgnr = dctVolgnr;
    }

    public BsmDocInfo(Long dctVolgnr, String naam, Date datum) {
        this.dctVolgnr = dctVolgnr;
        this.naam = naam;
        this.datum = datum;
    }

    public Long getDctVolgnr() {
        return dctVolgnr;
    }

    public void setDctVolgnr(Long dctVolgnr) {
        this.dctVolgnr = dctVolgnr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Long getGrootte() {
        return grootte;
    }

    public void setGrootte(Long grootte) {
        this.grootte = grootte;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getDatumOpname() {
        return datumOpname;
    }

    public void setDatumOpname(Date datumOpname) {
        this.datumOpname = datumOpname;
    }

    public Date getDatumGeraadpleegd() {
        return datumGeraadpleegd;
    }

    public void setDatumGeraadpleegd(Date datumGeraadpleegd) {
        this.datumGeraadpleegd = datumGeraadpleegd;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dctVolgnr != null ? dctVolgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmDocInfo)) {
            return false;
        }
        BsmDocInfo other = (BsmDocInfo) object;
        if ((this.dctVolgnr == null && other.dctVolgnr != null) || (this.dctVolgnr != null && !this.dctVolgnr.equals(other.dctVolgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmDocInfo[ dctVolgnr=" + dctVolgnr + " ]";
    }

    public List<BsmDocumentType> getBsmDocumentTypeCollection() {
        return bsmDocumentTypeCollection;
    }

    public void setBsmDocumentTypeCollection(List<BsmDocumentType> bsmDocumentTypeCollection) {
        this.bsmDocumentTypeCollection = bsmDocumentTypeCollection;
    }
    
}
