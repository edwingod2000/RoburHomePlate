/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "BSM_WEDSTRIJDEN_INCL_SERIES")
@NamedQueries({
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findAll", query = "SELECT b FROM BsmVWedstrijdenInclSeries b"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByVolgnr", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByDatum", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByThuisUit", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.thuisUit = :thuisUit"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByTegenstander", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.tegenstander = :tegenstander"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByTijdstip", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.tijdstip = :tijdstip"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByWedstrijdNummer", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.wedstrijdNummer = :wedstrijdNummer"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByTeamNaam", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.teamNaam = :teamNaam"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByOpmerking", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.opmerking = :opmerking"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByTypeWedstrijd", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.typeWedstrijd = :typeWedstrijd"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByScheidsrechters", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.scheidsrechters = :scheidsrechters"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findBySoort", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.soort = :soort"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByAfgelast", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.afgelast = :afgelast"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByVeldInfo", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.veldInfo = :veldInfo"),
    @NamedQuery(name = "BsmVWedstrijdenInclSeries.findByNaam", query = "SELECT b FROM BsmVWedstrijdenInclSeries b WHERE b.naam = :naam")})
public class BsmVWedstrijdenInclSeries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "VOLGNR")
    @Id
    private BigInteger volgnr;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "THUIS_UIT")
    private String thuisUit;
    @Column(name = "TEGENSTANDER")
    private String tegenstander;
    @Column(name = "TIJDSTIP")
    private String tijdstip;
    @Column(name = "WEDSTRIJD_NUMMER")
    private String wedstrijdNummer;
    @Column(name = "TEAM_NAAM")
    private String teamNaam;
    @Column(name = "OPMERKING")
    private String opmerking;
    @Column(name = "TYPE_WEDSTRIJD")
    private String typeWedstrijd;
    @Column(name = "SCHEIDSRECHTERS")
    private String scheidsrechters;
    @Column(name = "SOORT")
    private String soort;
    @Column(name = "AFGELAST")
    private String afgelast;
    @Column(name = "VELD_INFO")
    private BigInteger veldInfo;
    @Column(name = "NAAM")
    private String naam;
    @Column(name = "VELD_OMSCHRIJVING")
    private String veldOmschrijving;

    public BsmVWedstrijdenInclSeries() {
    }

    public BigInteger getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(BigInteger volgnr) {
        this.volgnr = volgnr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getThuisUit() {
        return thuisUit;
    }

    public void setThuisUit(String thuisUit) {
        this.thuisUit = thuisUit;
    }

    public String getTegenstander() {
        return tegenstander;
    }

    public void setTegenstander(String tegenstander) {
        this.tegenstander = tegenstander;
    }

    public String getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(String tijdstip) {
        this.tijdstip = tijdstip;
    }

    public String getWedstrijdNummer() {
        return wedstrijdNummer;
    }

    public void setWedstrijdNummer(String wedstrijdNummer) {
        this.wedstrijdNummer = wedstrijdNummer;
    }

    public String getTeamNaam() {
        return teamNaam;
    }

    public void setTeamNaam(String teamNaam) {
        this.teamNaam = teamNaam;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getTypeWedstrijd() {
        return typeWedstrijd;
    }

    public void setTypeWedstrijd(String typeWedstrijd) {
        this.typeWedstrijd = typeWedstrijd;
    }

    public String getScheidsrechters() {
        return scheidsrechters;
    }

    public void setScheidsrechters(String scheidsrechters) {
        this.scheidsrechters = scheidsrechters;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getAfgelast() {
        return afgelast;
    }

    public void setAfgelast(String afgelast) {
        this.afgelast = afgelast;
    }

    public BigInteger getVeldInfo() {
        return veldInfo;
    }

    public void setVeldInfo(BigInteger veldInfo) {
        this.veldInfo = veldInfo;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVeldOmschrijving() {
        return veldOmschrijving;
    }

    public void setVeldOmschrijving(String veldOmschrijving) {
        this.veldOmschrijving = veldOmschrijving;
    }

    public String getDisplayTeamThuis() {
        if (getThuisUit().equals("T")) {
            if (getNaam() != null) {
                return getNaam();
            } else {
                return getTeamNaam();
            }
        } else {
            return getTegenstander();
        }
    }
    
    public String getDisplayTeamUit() {
        if (getThuisUit().equals("T")) {
            return getTegenstander();
        } else {
            return getNaam();
        }
    }

    public String getDisplayInfo() {
        StringBuffer result = new StringBuffer();
        if (opmerking != null) {
            result.append(opmerking);
        }
        if (scheidsrechters != null) {
            result.append(" Scheidsrechter(s): ");
            result.append(scheidsrechters);
        }

        return result.toString();
    }
    
}
