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
import javax.persistence.FetchType;
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
@Table(name = "SOORT_DEELNEMERS")
@NamedQueries({
    @NamedQuery(name = "BsmSoortDeelnemers.findAll", query = "SELECT b FROM BsmSoortDeelnemers b"),
    @NamedQuery(name = "BsmSoortDeelnemers.findByVolgnr", query = "SELECT b FROM BsmSoortDeelnemers b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmSoortDeelnemers.findByOmschrijving", query = "SELECT b FROM BsmSoortDeelnemers b WHERE b.omschrijving = :omschrijving")})
public class BsmSoortDeelnemers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SoortDeelnemersSequence")
    @SequenceGenerator(name="SoortDeelnemersSequence", sequenceName="SDN_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @OneToMany(mappedBy = "sdsVolgnr", fetch = FetchType.EAGER)
    private Collection<BsmDeelnemers> bsmDeelnemersCollection;

    public BsmSoortDeelnemers() {
    }

    public BsmSoortDeelnemers(Long volgnr) {
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

    public Collection<BsmDeelnemers> getBsmDeelnemersCollection() {
        return bsmDeelnemersCollection;
    }

    public void setBsmDeelnemersCollection(Collection<BsmDeelnemers> bsmDeelnemersCollection) {
        this.bsmDeelnemersCollection = bsmDeelnemersCollection;
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
        if (!(object instanceof BsmSoortDeelnemers)) {
            return false;
        }
        BsmSoortDeelnemers other = (BsmSoortDeelnemers) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmSoortDeelnemers[volgnr=" + volgnr + "]";
    }

}
