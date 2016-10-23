/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "BSM_TYPE_LEDEN")
@NamedQueries({
    @NamedQuery(name = "BsmTypeLeden.findAll", query = "SELECT b FROM BsmTypeLeden b"),
    @NamedQuery(name = "BsmTypeLeden.findByVolgnr", query = "SELECT b FROM BsmTypeLeden b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmTypeLeden.findByCode", query = "SELECT b FROM BsmTypeLeden b WHERE b.code = :code"),
    @NamedQuery(name = "BsmTypeLeden.findByOmschrijving", query = "SELECT b FROM BsmTypeLeden b WHERE b.omschrijving = :omschrijving")})
public class BsmTypeLeden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TypeLedenSequence")
    @SequenceGenerator(name="TypeLedenSequence", sequenceName="TLD_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @OneToMany(mappedBy = "tldVolgnr")
    private Collection<BsmLeden> bsmLedenCollection;

    public BsmTypeLeden() {
    }

    public BsmTypeLeden(Long volgnr) {
        this.volgnr = volgnr;
    }

    public BsmTypeLeden(Long volgnr, String code, String omschrijving) {
        this.volgnr = volgnr;
        this.code = code;
        this.omschrijving = omschrijving;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Collection<BsmLeden> getBsmLedenCollection() {
        return bsmLedenCollection;
    }

    public void setBsmLedenCollection(Collection<BsmLeden> bsmLedenCollection) {
        this.bsmLedenCollection = bsmLedenCollection;
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
        if (!(object instanceof BsmTypeLeden)) {
            return false;
        }
        BsmTypeLeden other = (BsmTypeLeden) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTypeLeden[volgnr=" + volgnr + "]";
    }

}
