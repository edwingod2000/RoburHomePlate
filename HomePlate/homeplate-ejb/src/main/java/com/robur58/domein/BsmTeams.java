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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "TEAMS")
@NamedQueries({
    @NamedQuery(name = "BsmTeams.findAll", query = "SELECT b FROM BsmTeams b"),
    @NamedQuery(name = "BsmTeams.findByVolgnr", query = "SELECT b FROM BsmTeams b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmTeams.findByNaam", query = "SELECT b FROM BsmTeams b WHERE b.naam = :naam"),
    @NamedQuery(name = "BsmTeams.findByKlasse", query = "SELECT b FROM BsmTeams b WHERE b.klasse = :klasse"),
    @NamedQuery(name = "BsmTeams.findBySoort", query = "SELECT b FROM BsmTeams b WHERE b.soort = :soort"),
    @NamedQuery(name = "BsmTeams.findByHomepage", query = "SELECT b FROM BsmTeams b WHERE b.homepage = :homepage"),
    @NamedQuery(name = "BsmTeams.findByFoto", query = "SELECT b FROM BsmTeams b WHERE b.foto = :foto"),
    @NamedQuery(name = "BsmTeams.findByGebruikersid", query = "SELECT b FROM BsmTeams b WHERE b.gebruikersid = :gebruikersid"),
    @NamedQuery(name = "BsmTeams.findByDisplayVolgnr", query = "SELECT b FROM BsmTeams b WHERE b.displayVolgnr = :displayVolgnr"),
    @NamedQuery(name = "BsmTeams.findBySponsornaam", query = "SELECT b FROM BsmTeams b WHERE b.sponsornaam = :sponsornaam"),
    @NamedQuery(name = "BsmTeams.findBySponsorfoto", query = "SELECT b FROM BsmTeams b WHERE b.sponsorfoto = :sponsorfoto"),
    @NamedQuery(name = "BsmTeams.findBySponsorurl", query = "SELECT b FROM BsmTeams b WHERE b.sponsorurl = :sponsorurl"),
    @NamedQuery(name = "BsmTeams.findBySoortAndNotAll", query = "SELECT b FROM BsmTeams b WHERE b.soort = :soort and b.volgnr < 999000 order by b.displayVolgnr"),
    @NamedQuery(name = "BsmTeams.findHoofdteamBySoort", query = "SELECT b FROM BsmTeams b WHERE b.soort = :soort and b.hoofdteam = 'J'"),
    @NamedQuery(name = "BsmTeams.findByStandurl", query = "SELECT b FROM BsmTeams b WHERE b.standurl = :standurl")})
public class BsmTeams implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TeamSequence")
    @SequenceGenerator(name="TeamSequence", sequenceName="TEM_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "NAAM")
    private String naam;
    @Column(name = "KLASSE")
    private String klasse;
    @Column(name = "SOORT")
    private String soort;
    @Column(name = "HOMEPAGE")
    private String homepage;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "GEBRUIKERSID")
    private String gebruikersid;
    @Column(name = "DISPLAY_VOLGNR")
    private Long displayVolgnr;
    @Lob
    @Column(name = "VRIJETEKST")
    private String vrijetekst;
    @Column(name = "SPONSORNAAM")
    private String sponsornaam;
    @Column(name = "SPONSORFOTO")
    private String sponsorfoto;
    @Column(name = "SPONSORURL")
    private String sponsorurl;
    @Column(name = "STANDURL")
    private String standurl;
    @Column(name = "HOOFDTEAM")
    private String hoofdteam;
    
    public BsmTeams() {
    }

    public BsmTeams(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGebruikersid() {
        return gebruikersid;
    }

    public void setGebruikersid(String gebruikersid) {
        this.gebruikersid = gebruikersid;
    }

    public Long getDisplayVolgnr() {
        return displayVolgnr;
    }

    public void setDisplayVolgnr(Long displayVolgnr) {
        this.displayVolgnr = displayVolgnr;
    }

    public String getVrijetekst() {
        return vrijetekst;
    }

    public void setVrijetekst(String vrijetekst) {
        this.vrijetekst = vrijetekst;
    }

    public String getSponsornaam() {
        return sponsornaam;
    }

    public void setSponsornaam(String sponsornaam) {
        this.sponsornaam = sponsornaam;
    }

    public String getSponsorfoto() {
        return sponsorfoto;
    }

    public void setSponsorfoto(String sponsorfoto) {
        this.sponsorfoto = sponsorfoto;
    }

    public String getSponsorurl() {
        return sponsorurl;
    }

    public void setSponsorurl(String sponsorurl) {
        this.sponsorurl = sponsorurl;
    }

    public String getStandurl() {
        return standurl;
    }

    public void setStandurl(String standurl) {
        this.standurl = standurl;
    }

    public String getHoofdteam() {
        return hoofdteam;
    }

    public void setHoofdteam(String hoofdteam) {
        this.hoofdteam = hoofdteam;
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
        if (!(object instanceof BsmTeams)) {
            return false;
        }
        BsmTeams other = (BsmTeams) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTeams[volgnr=" + volgnr + "]";
    }

}
