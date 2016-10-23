/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "BSM_KALENDER")
@NamedQueries({
    @NamedQuery(name = "BsmKalender.findAll", query = "SELECT b FROM BsmKalender b"),
    @NamedQuery(name = "BsmKalender.findByVolgnr", query = "SELECT b FROM BsmKalender b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmKalender.findByDatum", query = "SELECT b FROM BsmKalender b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmKalender.findByTitel", query = "SELECT b FROM BsmKalender b WHERE b.titel = :titel"),
    @NamedQuery(name = "BsmKalender.findByGreaterThanDatum", query = "SELECT b FROM BsmKalender b WHERE b.datum > :datum order by b.datum asc"),
    @NamedQuery(name = "BsmKalender.findByOmschrijving", query = "SELECT b FROM BsmKalender b WHERE b.omschrijving = :omschrijving")})
public class BsmKalender implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KalenderSequence")
    @SequenceGenerator(name="KalenderSequence", sequenceName="KLR_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "TITEL")
    private String titel;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;

    public BsmKalender() {
    }

    public BsmKalender(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
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
        if (!(object instanceof BsmKalender)) {
            return false;
        }
        BsmKalender other = (BsmKalender) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmKalender[volgnr=" + volgnr + "]";
    }

}
