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
@Table(name = "BSM_WEDSTRIJDEN_TOTAAL")
@NamedQueries({
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findAll", query = "SELECT b FROM BsmVWedstrijdenTotaal b"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByVolgnr", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByDatum", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByThuisUit", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.thuisUit = :thuisUit"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByTegenstander", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.tegenstander = :tegenstander"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByTijdstip", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.tijdstip = :tijdstip"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByWedstrijdNummer", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.wedstrijdNummer = :wedstrijdNummer"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByTeamNaam", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.teamNaam = :teamNaam"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByOpmerking", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.opmerking = :opmerking"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByTypeWedstrijd", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.typeWedstrijd = :typeWedstrijd"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByScheidsrechters", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.scheidsrechters = :scheidsrechters"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findBySoort", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.soort = :soort"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByAfgelast", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.afgelast = :afgelast"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByVeldInfo", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.veldInfo = :veldInfo"),
    @NamedQuery(name = "BsmVWedstrijdenTotaal.findByNaam", query = "SELECT b FROM BsmVWedstrijdenTotaal b WHERE b.naam = :naam")})
public class BsmVWedstrijdenTotaal implements Serializable {
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

    public BsmVWedstrijdenTotaal() {
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

}
