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
@Table(name = "WEDSTRIJDEN")
@NamedQueries({
    @NamedQuery(name = "BsmWedstrijden.findAll", query = "SELECT b FROM BsmWedstrijden b"),
    @NamedQuery(name = "BsmWedstrijden.findByVolgnr", query = "SELECT b FROM BsmWedstrijden b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmWedstrijden.findByDatum", query = "SELECT b FROM BsmWedstrijden b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmWedstrijden.findByThuisUit", query = "SELECT b FROM BsmWedstrijden b WHERE b.thuisUit = :thuisUit"),
    @NamedQuery(name = "BsmWedstrijden.findByTegenstander", query = "SELECT b FROM BsmWedstrijden b WHERE b.tegenstander = :tegenstander"),
    @NamedQuery(name = "BsmWedstrijden.findByTijdstip", query = "SELECT b FROM BsmWedstrijden b WHERE b.tijdstip = :tijdstip"),
    @NamedQuery(name = "BsmWedstrijden.findByPuntenVoor", query = "SELECT b FROM BsmWedstrijden b WHERE b.puntenVoor = :puntenVoor"),
    @NamedQuery(name = "BsmWedstrijden.findByPuntenTegen", query = "SELECT b FROM BsmWedstrijden b WHERE b.puntenTegen = :puntenTegen"),
    @NamedQuery(name = "BsmWedstrijden.findBySoort", query = "SELECT b FROM BsmWedstrijden b WHERE b.soort = :soort"),
    @NamedQuery(name = "BsmWedstrijden.findByTeamNaam", query = "SELECT b FROM BsmWedstrijden b WHERE b.teamNaam = :teamNaam"),
    @NamedQuery(name = "BsmWedstrijden.findByOpmerking", query = "SELECT b FROM BsmWedstrijden b WHERE b.opmerking = :opmerking"),
    @NamedQuery(name = "BsmWedstrijden.findByTypeWedstrijd", query = "SELECT b FROM BsmWedstrijden b WHERE b.typeWedstrijd = :typeWedstrijd"),
    @NamedQuery(name = "BsmWedstrijden.findByScheidsrechters", query = "SELECT b FROM BsmWedstrijden b WHERE b.scheidsrechters = :scheidsrechters"),
    @NamedQuery(name = "BsmWedstrijden.findByWedstrijdNummer", query = "SELECT b FROM BsmWedstrijden b WHERE b.wedstrijdNummer = :wedstrijdNummer"),
    @NamedQuery(name = "BsmWedstrijden.findByArchief", query = "SELECT b FROM BsmWedstrijden b WHERE b.archief = :archief"),
    @NamedQuery(name = "BsmWedstrijden.findByYear", query = "SELECT b FROM BsmWedstrijden b WHERE b.datum > :fromDate AND b.archief = :archief ORDER BY b.datum, b.tijdstip"),
    @NamedQuery(name = "BsmWedstrijden.findBySoortAndYear", query = "SELECT b FROM BsmWedstrijden b WHERE b.soort = :soort AND b.datum > :fromDate AND b.archief = :archief ORDER BY b.datum, b.tijdstip"),
    @NamedQuery(name = "BsmWedstrijden.findByTeamVolgnrAndYear", query = "SELECT b FROM BsmWedstrijden b WHERE b.teamVolgnr.volgnr = :teamVolgnr AND b.datum > :fromDate AND b.archief = :archief ORDER BY b.datum, b.tijdstip"),
    @NamedQuery(name = "BsmWedstrijden.findFutureGamesByTeamVolgnr", query = "SELECT b FROM BsmWedstrijden b WHERE b.teamVolgnr.volgnr = :teamVolgnr AND b.archief = :archief AND b.datum > :fromDate ORDER BY b.datum, b.tijdstip desc"),
    @NamedQuery(name = "BsmWedstrijden.findByAfgelast", query = "SELECT b FROM BsmWedstrijden b WHERE b.afgelast = :afgelast")})
public class BsmWedstrijden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WedstrijdenSequence")
    @SequenceGenerator(name="WedstrijdenSequence", sequenceName="WED_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "THUIS_UIT")
    private String thuisUit;
    @Column(name = "TEGENSTANDER")
    private String tegenstander;
    @Column(name = "TIJDSTIP")
    private String tijdstip;
    @Column(name = "PUNTEN_VOOR")
    private String puntenVoor;
    @Column(name = "PUNTEN_TEGEN")
    private String puntenTegen;
    @Column(name = "SOORT")
    private String soort;
    @Column(name = "TEAM_NAAM")
    private String teamNaam;
    @Column(name = "OPMERKING")
    private String opmerking;
    @Column(name = "TYPE_WEDSTRIJD")
    private String typeWedstrijd;
    @Column(name = "SCHEIDSRECHTERS")
    private String scheidsrechters;
    @Column(name = "WEDSTRIJD_NUMMER")
    private String wedstrijdNummer;
    @Column(name = "ARCHIEF")
    private String archief;
    @Column(name = "AFGELAST")
    private String afgelast;
    @JoinColumn(name = "VELD_INFO", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmSpeellokaties veldInfo;
    @JoinColumn(name = "TEAM_VOLGNR", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmTeams teamVolgnr;

    public BsmWedstrijden() {
    }

    public BsmWedstrijden(Long volgnr) {
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

    public String getPuntenVoor() {
        return puntenVoor;
    }

    public void setPuntenVoor(String puntenVoor) {
        this.puntenVoor = puntenVoor;
    }

    public String getPuntenTegen() {
        return puntenTegen;
    }

    public void setPuntenTegen(String puntenTegen) {
        this.puntenTegen = puntenTegen;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
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

    public String getWedstrijdNummer() {
        return wedstrijdNummer;
    }

    public void setWedstrijdNummer(String wedstrijdNummer) {
        this.wedstrijdNummer = wedstrijdNummer;
    }

    public String getArchief() {
        return archief;
    }

    public void setArchief(String archief) {
        this.archief = archief;
    }

    public String getAfgelast() {
        return afgelast;
    }

    public void setAfgelast(String afgelast) {
        this.afgelast = afgelast;
    }

    public BsmSpeellokaties getVeldInfo() {
        return veldInfo;
    }

    public void setVeldInfo(BsmSpeellokaties veldInfo) {
        this.veldInfo = veldInfo;
    }

    public BsmTeams getTeamVolgnr() {
        return teamVolgnr;
    }

    public void setTeamVolgnr(BsmTeams teamVolgnr) {
        this.teamVolgnr = teamVolgnr;
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

    public String getDisplayTeamThuis() {
        String teamThuis = null;
        if (thuisUit.equals("T")) {
            if (teamVolgnr == null) {
                teamThuis = teamNaam;
            } else {
                teamThuis = teamVolgnr.getNaam();
            }
        } else {
            teamThuis = tegenstander;
        }

        return teamThuis;
    }

    public String getDisplayTeamUit() {
        String teamUit = null;
        if (thuisUit.equals("T")) {
            teamUit = tegenstander;
        } else {
            if (teamVolgnr == null) {
                teamUit = teamNaam;
            } else {
                teamUit = teamVolgnr.getNaam();
            }
        }

        return teamUit;
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
        if (!(object instanceof BsmWedstrijden)) {
            return false;
        }
        BsmWedstrijden other = (BsmWedstrijden) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmWedstrijden[volgnr=" + volgnr + "]";
    }

}
