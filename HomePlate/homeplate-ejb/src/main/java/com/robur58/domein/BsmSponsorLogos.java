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
@Table(name = "BSM_SPONSOR_LOGOS")
@NamedQueries({
    @NamedQuery(name = "BsmSponsorLogos.findAll", query = "SELECT b FROM BsmSponsorLogos b"),
    @NamedQuery(name = "BsmSponsorLogos.findByVolgnr", query = "SELECT b FROM BsmSponsorLogos b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmSponsorLogos.findByTitel", query = "SELECT b FROM BsmSponsorLogos b WHERE b.titel = :titel"),
    @NamedQuery(name = "BsmSponsorLogos.findByUrl", query = "SELECT b FROM BsmSponsorLogos b WHERE b.url = :url"),
    @NamedQuery(name = "BsmSponsorLogos.findByBestandsnaam", query = "SELECT b FROM BsmSponsorLogos b WHERE b.bestandsnaam = :bestandsnaam"),
    @NamedQuery(name = "BsmSponsorLogos.findByDisplayVolgnr", query = "SELECT b FROM BsmSponsorLogos b WHERE b.displayVolgnr = :displayVolgnr"),
    @NamedQuery(name = "BsmSponsorLogos.findByTonenInCarroussel", query = "SELECT b FROM BsmSponsorLogos b WHERE b.tonenInCarroussel = :tonenInCarroussel AND b.bestandsnaam is not null ORDER BY b.displayVolgnr")})
public class BsmSponsorLogos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SponsorLogoSequence")
    @SequenceGenerator(name="SponsorLogoSequence", sequenceName="SLO_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "TITEL")
    private String titel;
    @Column(name = "URL")
    private String url;
    @Column(name = "BESTANDSNAAM")
    private String bestandsnaam;
    @Column(name = "DISPLAY_VOLGNR")
    private Long displayVolgnr;
    @Column(name = "TONEN_IN_CARROUSSEL")
    private String tonenInCarroussel;

    public BsmSponsorLogos() {
    }

    public BsmSponsorLogos(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBestandsnaam() {
        return bestandsnaam;
    }

    public void setBestandsnaam(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }

    public Long getDisplayVolgnr() {
        return displayVolgnr;
    }

    public void setDisplayVolgnr(Long displayVolgnr) {
        this.displayVolgnr = displayVolgnr;
    }

    public String getTonenInCarroussel() {
        return tonenInCarroussel;
    }

    public void setTonenInCarroussel(String tonenInCarroussel) {
        this.tonenInCarroussel = tonenInCarroussel;
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
        if (!(object instanceof BsmSponsorLogos)) {
            return false;
        }
        BsmSponsorLogos other = (BsmSponsorLogos) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmSponsorLogos[volgnr=" + volgnr + "]";
    }

}
