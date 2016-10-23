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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DEELNEMERS")
@NamedQueries({
    @NamedQuery(name = "BsmDeelnemers.findAll", query = "SELECT b FROM BsmDeelnemers b"),
    @NamedQuery(name = "BsmDeelnemers.findByVolgnr", query = "SELECT b FROM BsmDeelnemers b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmDeelnemers.findByAchternaam", query = "SELECT b FROM BsmDeelnemers b WHERE b.achternaam = :achternaam"),
    @NamedQuery(name = "BsmDeelnemers.findByTussenvoegsel", query = "SELECT b FROM BsmDeelnemers b WHERE b.tussenvoegsel = :tussenvoegsel"),
    @NamedQuery(name = "BsmDeelnemers.findByVoornaam", query = "SELECT b FROM BsmDeelnemers b WHERE b.voornaam = :voornaam"),
    @NamedQuery(name = "BsmDeelnemers.findByPosities", query = "SELECT b FROM BsmDeelnemers b WHERE b.posities = :posities"),
    @NamedQuery(name = "BsmDeelnemers.findByTemVolgnr", query = "SELECT b FROM BsmDeelnemers b WHERE b.temVolgnr = :temVolgnr order by b.sdsVolgnr.volgnr, b.achternaam, b.voornaam"),
    @NamedQuery(name = "BsmDeelnemers.findByTemVolgnrExclusiveNonRegistered", query = "SELECT distinct b FROM BsmDeelnemers b, BsmGebruikers g WHERE b.temVolgnr = :temVolgnr and (b.lidnummer = g.lidNummer and b.lidnummer is not null and g.lidNummer is not null) order by b.sdsVolgnr.volgnr, b.achternaam, b.voornaam"),
    @NamedQuery(name = "BsmDeelnemers.findByFoto", query = "SELECT b FROM BsmDeelnemers b WHERE b.foto = :foto"),
    @NamedQuery(name = "BsmDeelnemers.findByRugnummer", query = "SELECT b FROM BsmDeelnemers b WHERE b.rugnummer = :rugnummer"),
    @NamedQuery(name = "BsmDeelnemers.findByGeboortedatum", query = "SELECT b FROM BsmDeelnemers b WHERE b.geboortedatum = :geboortedatum"),
    @NamedQuery(name = "BsmDeelnemers.findByLidnummer", query = "SELECT b FROM BsmDeelnemers b WHERE b.lidnummer = :lidnummer"),
    @NamedQuery(name = "BsmDeelnemers.findByStraatnaam", query = "SELECT b FROM BsmDeelnemers b WHERE b.straatnaam = :straatnaam"),
    @NamedQuery(name = "BsmDeelnemers.findByHuisnummer", query = "SELECT b FROM BsmDeelnemers b WHERE b.huisnummer = :huisnummer"),
    @NamedQuery(name = "BsmDeelnemers.findByPostcode", query = "SELECT b FROM BsmDeelnemers b WHERE b.postcode = :postcode"),
    @NamedQuery(name = "BsmDeelnemers.findByPlaats", query = "SELECT b FROM BsmDeelnemers b WHERE b.plaats = :plaats"),
    @NamedQuery(name = "BsmDeelnemers.findByTelefoon", query = "SELECT b FROM BsmDeelnemers b WHERE b.telefoon = :telefoon"),
    @NamedQuery(name = "BsmDeelnemers.findByAlternatiefmob", query = "SELECT b FROM BsmDeelnemers b WHERE b.alternatiefmob = :alternatiefmob"),
    @NamedQuery(name = "BsmDeelnemers.findByEmail", query = "SELECT b FROM BsmDeelnemers b WHERE b.email = :email"),
    @NamedQuery(name = "BsmDeelnemers.findByBegindatumlid", query = "SELECT b FROM BsmDeelnemers b WHERE b.begindatumlid = :begindatumlid")})
public class BsmDeelnemers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DeelnemersSequence")
    @SequenceGenerator(name="DeelnemersSequence", sequenceName="DNR_SEQ", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "ACHTERNAAM")
    private String achternaam;
    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;
    @Column(name = "VOORNAAM")
    private String voornaam;
    @Column(name = "POSITIES")
    private String posities;
    @Column(name = "TEM_VOLGNR")
    private Long temVolgnr;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "RUGNUMMER")
    private Long rugnummer;
    @Column(name = "GEBOORTEDATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geboortedatum;
    @Column(name = "LIDNUMMER")
    private String lidnummer;
    @Column(name = "STRAATNAAM")
    private String straatnaam;
    @Column(name = "HUISNUMMER")
    private String huisnummer;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "PLAATS")
    private String plaats;
    @Column(name = "TELEFOON")
    private String telefoon;
    @Column(name = "ALTERNATIEFMOB")
    private String alternatiefmob;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BEGINDATUMLID")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begindatumlid;
    @JoinColumn(name = "SDS_VOLGNR", referencedColumnName = "VOLGNR")
    @ManyToOne(fetch = FetchType.EAGER)
    private BsmSoortDeelnemers sdsVolgnr;

    public BsmDeelnemers() {
    }

    public BsmDeelnemers(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getPosities() {
        return posities;
    }

    public void setPosities(String posities) {
        this.posities = posities;
    }

    public Long getTemVolgnr() {
        return temVolgnr;
    }

    public void setTemVolgnr(Long temVolgnr) {
        this.temVolgnr = temVolgnr;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getRugnummer() {
        return rugnummer;
    }

    public void setRugnummer(Long rugnummer) {
        this.rugnummer = rugnummer;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getLidnummer() {
        return lidnummer;
    }

    public void setLidnummer(String lidnummer) {
        this.lidnummer = lidnummer;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getAlternatiefmob() {
        return alternatiefmob;
    }

    public void setAlternatiefmob(String alternatiefmob) {
        this.alternatiefmob = alternatiefmob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBegindatumlid() {
        return begindatumlid;
    }

    public void setBegindatumlid(Date begindatumlid) {
        this.begindatumlid = begindatumlid;
    }

    public BsmSoortDeelnemers getSdsVolgnr() {
        return sdsVolgnr;
    }

    public void setSdsVolgnr(BsmSoortDeelnemers sdsVolgnr) {
        this.sdsVolgnr = sdsVolgnr;
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
        if (!(object instanceof BsmDeelnemers)) {
            return false;
        }
        BsmDeelnemers other = (BsmDeelnemers) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmDeelnemers[volgnr=" + volgnr + "]";
    }

}
