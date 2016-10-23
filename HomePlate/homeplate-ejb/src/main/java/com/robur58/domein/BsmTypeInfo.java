/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "TYPE_INFO")
@NamedQueries({
    @NamedQuery(name = "BsmTypeInfo.findAll", query = "SELECT b FROM BsmTypeInfo b"),
    @NamedQuery(name = "BsmTypeInfo.findByVolgnr", query = "SELECT b FROM BsmTypeInfo b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmTypeInfo.findByOmschrijving", query = "SELECT b FROM BsmTypeInfo b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmTypeInfo.findByOmschrijvingEn", query = "SELECT b FROM BsmTypeInfo b WHERE b.omschrijvingEn = :omschrijvingEn"),
    @NamedQuery(name = "BsmTypeInfo.findByArchief", query = "SELECT b FROM BsmTypeInfo b WHERE b.archief = :archief"),
    @NamedQuery(name = "BsmTypeInfo.findByLaatsteUpdate", query = "SELECT b FROM BsmTypeInfo b WHERE b.laatsteUpdate = :laatsteUpdate")})
public class BsmTypeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TypeInfoSequence")
    @SequenceGenerator(name="TypeInfoSequence", sequenceName="TIO_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Column(name = "OMSCHRIJVING_EN")
    private String omschrijvingEn;
    @Column(name = "ARCHIEF")
    private String archief;
    @Column(name = "LAATSTE_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date laatsteUpdate;
    @OneToMany(mappedBy = "typeInfo")
    private Collection<BsmInfo> bsmInfoCollection;

    public BsmTypeInfo() {
    }

    public BsmTypeInfo(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijvingEn() {
        return omschrijvingEn;
    }

    public void setOmschrijvingEn(String omschrijvingEn) {
        this.omschrijvingEn = omschrijvingEn;
    }

    public String getArchief() {
        return archief;
    }

    public void setArchief(String archief) {
        this.archief = archief;
    }

    public Date getLaatsteUpdate() {
        return laatsteUpdate;
    }

    public void setLaatsteUpdate(Date laatsteUpdate) {
        this.laatsteUpdate = laatsteUpdate;
    }

    public Collection<BsmInfo> getBsmInfoCollection() {
        return bsmInfoCollection;
    }

    public void setBsmInfoCollection(Collection<BsmInfo> bsmInfoCollection) {
        this.bsmInfoCollection = bsmInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volgnr != null ? volgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmTypeInfo)) {
            return false;
        }
        BsmTypeInfo other = (BsmTypeInfo) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTypeInfo[volgnr=" + volgnr + "]";
    }

}
