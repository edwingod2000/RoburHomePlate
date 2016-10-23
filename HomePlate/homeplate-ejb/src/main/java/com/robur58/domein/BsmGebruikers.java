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

@Entity
@Table(name = "GEBRUIKERS")
@NamedQueries({
    @NamedQuery(name = "BsmGebruikers.findAll", query = "SELECT b FROM BsmGebruikers b"),
    @NamedQuery(name = "BsmGebruikers.findByVolgnr", query = "SELECT b FROM BsmGebruikers b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmGebruikers.findByLidNummer", query = "SELECT b FROM BsmGebruikers b WHERE b.lidNummer = :lidNummer"),
    @NamedQuery(name = "BsmGebruikers.findByGebruikersid", query = "SELECT b FROM BsmGebruikers b WHERE b.gebruikersid = :gebruikersid"),
    @NamedQuery(name = "BsmGebruikers.findByAchternaam", query = "SELECT b FROM BsmGebruikers b WHERE b.achternaam = :achternaam"),
    @NamedQuery(name = "BsmGebruikers.findByTussenvoegsel", query = "SELECT b FROM BsmGebruikers b WHERE b.tussenvoegsel = :tussenvoegsel"),
    @NamedQuery(name = "BsmGebruikers.findByVoornaam", query = "SELECT b FROM BsmGebruikers b WHERE b.voornaam = :voornaam"),
    @NamedQuery(name = "BsmGebruikers.findByWachtwoord", query = "SELECT b FROM BsmGebruikers b WHERE b.wachtwoord = :wachtwoord"),
    @NamedQuery(name = "BsmGebruikers.findBySessionId", query = "SELECT b FROM BsmGebruikers b WHERE b.sessionId = :sessionId"),
    @NamedQuery(name = "BsmGebruikers.findByDatumInlog", query = "SELECT b FROM BsmGebruikers b WHERE b.datumInlog = :datumInlog"),
    @NamedQuery(name = "BsmGebruikers.findByDatumUitlog", query = "SELECT b FROM BsmGebruikers b WHERE b.datumUitlog = :datumUitlog"),
    @NamedQuery(name = "BsmGebruikers.findByTypeGebruiker", query = "SELECT b FROM BsmGebruikers b WHERE b.typeGebruiker = :typeGebruiker"),
    @NamedQuery(name = "BsmGebruikers.findByInitialen", query = "SELECT b FROM BsmGebruikers b WHERE b.initialen = :initialen"),
    @NamedQuery(name = "BsmGebruikers.findByEmail", query = "SELECT b FROM BsmGebruikers b WHERE b.email = :email"),
    @NamedQuery(name = "BsmGebruikers.findByRegistreerSid", query = "SELECT b FROM BsmGebruikers b WHERE b.registreerSid = :registreerSid")})
public class BsmGebruikers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GebruikersSequence")
    @SequenceGenerator(name="GebruikersSequence", sequenceName="GBR_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Basic(optional = false)
    @Column(name = "GEBRUIKERSID")
    private String gebruikersid;
    @Column(name = "ACHTERNAAM")
    private String achternaam;
    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;
    @Column(name = "VOORNAAM")
    private String voornaam;
    @Column(name = "WACHTWOORD")
    private String wachtwoord;
    @Column(name = "SESSION_ID")
    private String sessionId;
    @Column(name = "DATUM_INLOG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumInlog;
    @Column(name = "DATUM_UITLOG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumUitlog;
    @Column(name = "TYPE_GEBRUIKER")
    private String typeGebruiker;
    @Column(name = "INITIALEN")
    private String initialen;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFOONNUMMER")
    private String telefoonNummer;
    @Column(name = "LIDNUMMER")
    private String lidNummer;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "GEBOORTEDATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geboortedatum;
    @Column(name = "REGISTREERSID")
    private String registreerSid;
    @Column(name = "PAPIEREN_NIEUWS")
    private String papierenNieuws;
    @Column(name = "PELLIKAAN_AL_LID")
    private String pellikaanAlLid;
    @Column(name = "PELLIKAAN_MEEDOEN")
    private String pellikaanMeedoen;

    public BsmGebruikers() {
    }

    public BsmGebruikers(Long volgnr) {
        this.volgnr = volgnr;
    }

    public BsmGebruikers(Long volgnr, String gebruikersid) {
        this.volgnr = volgnr;
        this.gebruikersid = gebruikersid;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getGebruikersid() {
        return gebruikersid;
    }

    public void setGebruikersid(String gebruikersid) {
        this.gebruikersid = gebruikersid;
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

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDatumInlog() {
        return datumInlog;
    }

    public void setDatumInlog(Date datumInlog) {
        this.datumInlog = datumInlog;
    }

    public Date getDatumUitlog() {
        return datumUitlog;
    }

    public void setDatumUitlog(Date datumUitlog) {
        this.datumUitlog = datumUitlog;
    }

    public String getTypeGebruiker() {
        return typeGebruiker;
    }

    public void setTypeGebruiker(String typeGebruiker) {
        this.typeGebruiker = typeGebruiker;
    }

    public String getInitialen() {
        return initialen;
    }

    public void setInitialen(String initialen) {
        this.initialen = initialen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getLidNummer() {
        return lidNummer;
    }

    public void setLidNummer(String lidNummer) {
        this.lidNummer = lidNummer;
    }

    public String getLidnummer() {
        return lidNummer;
    }

    public void setLidnummer(String lidnummer) {
        this.lidNummer = lidnummer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getRegistreerSid() {
        return registreerSid;
    }

    public void setRegistreerSid(String registreerSid) {
        this.registreerSid = registreerSid;
    }

    public String getPapierenNieuws() {
        return papierenNieuws;
    }

    public void setPapierenNieuws(String papierenNieuws) {
        this.papierenNieuws = papierenNieuws;
    }

    public String getPellikaanAlLid() {
        return pellikaanAlLid;
    }

    public void setPellikaanAlLid(String pellikaanAlLid) {
        this.pellikaanAlLid = pellikaanAlLid;
    }

    public String getPellikaanMeedoen() {
        return pellikaanMeedoen;
    }

    public void setPellikaanMeedoen(String pellikaanMeedoen) {
        this.pellikaanMeedoen = pellikaanMeedoen;
    }
    
    public String getDisplayNaam() {
        StringBuilder builder = new StringBuilder();
        if (voornaam != null) {
           builder.append(voornaam);
            if (tussenvoegsel != null) {
                builder.append(" ");
            }
        }
        if (tussenvoegsel != null) {
            builder.append(tussenvoegsel);
        }
        if (achternaam != null) {
            builder.append(" ");
            builder.append(achternaam);
        }
        return builder.toString();
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
        if (!(object instanceof BsmGebruikers)) {
            return false;
        }
        BsmGebruikers other = (BsmGebruikers) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmGebruikers[volgnr=" + volgnr + "]";
    }

}
