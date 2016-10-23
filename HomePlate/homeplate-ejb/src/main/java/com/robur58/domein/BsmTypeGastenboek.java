/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
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

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "TYPE_GASTENBOEK")
@NamedQueries({
    @NamedQuery(name = "BsmTypeGastenboek.findAll", query = "SELECT b FROM BsmTypeGastenboek b"),
    @NamedQuery(name = "BsmTypeGastenboek.findByVolgnr", query = "SELECT b FROM BsmTypeGastenboek b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmTypeGastenboek.findByOmschrijving", query = "SELECT b FROM BsmTypeGastenboek b WHERE b.omschrijving = :omschrijving")})
public class BsmTypeGastenboek implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TypeGastenboekSequence")
    @SequenceGenerator(name="TypeGastenboekSequence", sequenceName="TGK_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;

    public BsmTypeGastenboek() {
    }

    public BsmTypeGastenboek(Long volgnr) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volgnr != null ? volgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmTypeGastenboek)) {
            return false;
        }
        BsmTypeGastenboek other = (BsmTypeGastenboek) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTypeGastenboek[volgnr=" + volgnr + "]";
    }

}
