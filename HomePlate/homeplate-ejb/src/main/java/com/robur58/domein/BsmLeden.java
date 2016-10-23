/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "BSM_LEDEN")
@NamedQueries({
    @NamedQuery(name = "BsmLeden.findAll", query = "SELECT b FROM BsmLeden b"),
    @NamedQuery(name = "BsmLeden.findByVolgnr", query = "SELECT b FROM BsmLeden b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmLeden.findByAchternaam", query = "SELECT b FROM BsmLeden b WHERE b.achternaam = :achternaam"),
    @NamedQuery(name = "BsmLeden.findByVoornaam", query = "SELECT b FROM BsmLeden b WHERE b.voornaam = :voornaam"),
    @NamedQuery(name = "BsmLeden.findByTussenvoegsel", query = "SELECT b FROM BsmLeden b WHERE b.tussenvoegsel = :tussenvoegsel"),
    @NamedQuery(name = "BsmLeden.findByLidnr", query = "SELECT b FROM BsmLeden b WHERE b.lidnr = :lidnr"),
    @NamedQuery(name = "BsmLeden.findByStraatnaam", query = "SELECT b FROM BsmLeden b WHERE b.straatnaam = :straatnaam"),
    @NamedQuery(name = "BsmLeden.findByHuisnummer", query = "SELECT b FROM BsmLeden b WHERE b.huisnummer = :huisnummer"),
    @NamedQuery(name = "BsmLeden.findByPostcode", query = "SELECT b FROM BsmLeden b WHERE b.postcode = :postcode"),
    @NamedQuery(name = "BsmLeden.findByWoonplaats", query = "SELECT b FROM BsmLeden b WHERE b.woonplaats = :woonplaats"),
    @NamedQuery(name = "BsmLeden.findByTelefoon", query = "SELECT b FROM BsmLeden b WHERE b.telefoon = :telefoon"),
    @NamedQuery(name = "BsmLeden.findByMobiel", query = "SELECT b FROM BsmLeden b WHERE b.mobiel = :mobiel"),
    @NamedQuery(name = "BsmLeden.findByEmail", query = "SELECT b FROM BsmLeden b WHERE b.email = :email"),
    @NamedQuery(name = "BsmLeden.findByGeboortedatum", query = "SELECT b FROM BsmLeden b WHERE b.geboortedatum = :geboortedatum"),
    @NamedQuery(name = "BsmLeden.findByBegindatumLid", query = "SELECT b FROM BsmLeden b WHERE b.begindatumLid = :begindatumLid"),
    @NamedQuery(name = "BsmLeden.findByEinddatumLid", query = "SELECT b FROM BsmLeden b WHERE b.einddatumLid = :einddatumLid"),
    @NamedQuery(name = "BsmLeden.findByAanhef", query = "SELECT b FROM BsmLeden b WHERE b.aanhef = :aanhef"),
    @NamedQuery(name = "BsmLeden.findByVoorletters", query = "SELECT b FROM BsmLeden b WHERE b.voorletters = :voorletters"),
    @NamedQuery(name = "BsmLeden.findByToevoegingAdres", query = "SELECT b FROM BsmLeden b WHERE b.toevoegingAdres = :toevoegingAdres"),
    @NamedQuery(name = "BsmLeden.findByGeboorteplaats", query = "SELECT b FROM BsmLeden b WHERE b.geboorteplaats = :geboorteplaats"),
    @NamedQuery(name = "BsmLeden.findByNationaliteit", query = "SELECT b FROM BsmLeden b WHERE b.nationaliteit = :nationaliteit"),
    @NamedQuery(name = "BsmLeden.findByGeslacht", query = "SELECT b FROM BsmLeden b WHERE b.geslacht = :geslacht"),
    @NamedQuery(name = "BsmLeden.findByMutatieDatum", query = "SELECT b FROM BsmLeden b WHERE b.mutatieDatum = :mutatieDatum"),
    @NamedQuery(name = "BsmLeden.findByRedenMutatie", query = "SELECT b FROM BsmLeden b WHERE b.redenMutatie = :redenMutatie"),
    @NamedQuery(name = "BsmLeden.findBySoortLid", query = "SELECT b FROM BsmLeden b WHERE b.soortLid = :soortLid"),
    @NamedQuery(name = "BsmLeden.findBySoortLidBond", query = "SELECT b FROM BsmLeden b WHERE b.soortLidBond = :soortLidBond"),
    @NamedQuery(name = "BsmLeden.findBySpelendLid", query = "SELECT b FROM BsmLeden b WHERE b.spelendLid = :spelendLid"),
    @NamedQuery(name = "BsmLeden.findByNietSpelendLid", query = "SELECT b FROM BsmLeden b WHERE b.nietSpelendLid = :nietSpelendLid"),
    @NamedQuery(name = "BsmLeden.findByTeam99", query = "SELECT b FROM BsmLeden b WHERE b.team99 = :team99"),
    @NamedQuery(name = "BsmLeden.findByTrCo", query = "SELECT b FROM BsmLeden b WHERE b.trCo = :trCo"),
    @NamedQuery(name = "BsmLeden.findByBegeleider", query = "SELECT b FROM BsmLeden b WHERE b.begeleider = :begeleider"),
    @NamedQuery(name = "BsmLeden.findByDonateur", query = "SELECT b FROM BsmLeden b WHERE b.donateur = :donateur"),
    @NamedQuery(name = "BsmLeden.findByKaderlid", query = "SELECT b FROM BsmLeden b WHERE b.kaderlid = :kaderlid"),
    @NamedQuery(name = "BsmLeden.findByAdverteerder", query = "SELECT b FROM BsmLeden b WHERE b.adverteerder = :adverteerder"),
    @NamedQuery(name = "BsmLeden.findByLvv", query = "SELECT b FROM BsmLeden b WHERE b.lvv = :lvv"),
    @NamedQuery(name = "BsmLeden.findByJaarLvv", query = "SELECT b FROM BsmLeden b WHERE b.jaarLvv = :jaarLvv"),
    @NamedQuery(name = "BsmLeden.findByEreLid", query = "SELECT b FROM BsmLeden b WHERE b.ereLid = :ereLid"),
    @NamedQuery(name = "BsmLeden.findByJaarEreLid", query = "SELECT b FROM BsmLeden b WHERE b.jaarEreLid = :jaarEreLid"),
    @NamedQuery(name = "BsmLeden.findByOmroepers", query = "SELECT b FROM BsmLeden b WHERE b.omroepers = :omroepers"),
    @NamedQuery(name = "BsmLeden.findByUmpire", query = "SELECT b FROM BsmLeden b WHERE b.umpire = :umpire"),
    @NamedQuery(name = "BsmLeden.findByWedstrSecr", query = "SELECT b FROM BsmLeden b WHERE b.wedstrSecr = :wedstrSecr"),
    @NamedQuery(name = "BsmLeden.findByTweedePenningm", query = "SELECT b FROM BsmLeden b WHERE b.tweedePenningm = :tweedePenningm"),
    @NamedQuery(name = "BsmLeden.findByLedenadmin", query = "SELECT b FROM BsmLeden b WHERE b.ledenadmin = :ledenadmin"),
    @NamedQuery(name = "BsmLeden.findByStrike", query = "SELECT b FROM BsmLeden b WHERE b.strike = :strike"),
    @NamedQuery(name = "BsmLeden.findByContributie", query = "SELECT b FROM BsmLeden b WHERE b.contributie = :contributie"),
    @NamedQuery(name = "BsmLeden.findByKaderkorting", query = "SELECT b FROM BsmLeden b WHERE b.kaderkorting = :kaderkorting"),
    @NamedQuery(name = "BsmLeden.findByGezinskorting", query = "SELECT b FROM BsmLeden b WHERE b.gezinskorting = :gezinskorting")})
public class BsmLeden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LedenSequence")
    @SequenceGenerator(name="LedenSequence", sequenceName="LDN_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "ACHTERNAAM")
    private String achternaam;
    @Column(name = "VOORNAAM")
    private String voornaam;
    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;
    @Column(name = "LIDNR")
    private String lidnr;
    @Column(name = "STRAATNAAM")
    private String straatnaam;
    @Column(name = "HUISNUMMER")
    private String huisnummer;
    @Column(name = "POSTCODE")
    private String postcode;
    @Column(name = "WOONPLAATS")
    private String woonplaats;
    @Column(name = "TELEFOON")
    private String telefoon;
    @Column(name = "MOBIEL")
    private String mobiel;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GEBOORTEDATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geboortedatum;
    @Column(name = "BEGINDATUM_LID")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begindatumLid;
    @Column(name = "EINDDATUM_LID")
    @Temporal(TemporalType.TIMESTAMP)
    private Date einddatumLid;
    @Column(name = "AANHEF")
    private String aanhef;
    @Column(name = "VOORLETTERS")
    private String voorletters;
    @Column(name = "TOEVOEGING_ADRES")
    private String toevoegingAdres;
    @Column(name = "GEBOORTEPLAATS")
    private String geboorteplaats;
    @Column(name = "NATIONALITEIT")
    private String nationaliteit;
    @Column(name = "GESLACHT")
    private String geslacht;
    @Column(name = "MUTATIE_DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mutatieDatum;
    @Column(name = "REDEN_MUTATIE")
    private String redenMutatie;
    @Column(name = "SOORT_LID")
    private String soortLid;
    @Column(name = "SOORT_LID_BOND")
    private String soortLidBond;
    @Column(name = "SPELEND_LID")
    private String spelendLid;
    @Column(name = "NIET_SPELEND_LID")
    private String nietSpelendLid;
    @Column(name = "TEAM_99")
    private String team99;
    @Column(name = "TR_CO")
    private String trCo;
    @Column(name = "BEGELEIDER")
    private String begeleider;
    @Column(name = "DONATEUR")
    private String donateur;
    @Column(name = "KADERLID")
    private String kaderlid;
    @Column(name = "ADVERTEERDER")
    private String adverteerder;
    @Column(name = "LVV")
    private String lvv;
    @Column(name = "JAAR_LVV")
    private String jaarLvv;
    @Column(name = "ERE_LID")
    private String ereLid;
    @Column(name = "JAAR_ERE_LID")
    private String jaarEreLid;
    @Column(name = "OMROEPERS")
    private String omroepers;
    @Column(name = "UMPIRE")
    private String umpire;
    @Column(name = "WEDSTR_SECR")
    private String wedstrSecr;
    @Column(name = "TWEEDE_PENNINGM")
    private String tweedePenningm;
    @Column(name = "LEDENADMIN")
    private String ledenadmin;
    @Column(name = "STRIKE")
    private String strike;
    @Column(name = "CONTRIBUTIE")
    private BigDecimal contributie;
    @Column(name = "KADERKORTING")
    private BigDecimal kaderkorting;
    @Column(name = "GEZINSKORTING")
    private BigDecimal gezinskorting;
    @JoinColumn(name = "TLD_VOLGNR", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmTypeLeden tldVolgnr;

    public BsmLeden() {
    }

    public BsmLeden(Long volgnr) {
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

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getLidnr() {
        return lidnr;
    }

    public void setLidnr(String lidnr) {
        this.lidnr = lidnr;
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

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getMobiel() {
        return mobiel;
    }

    public void setMobiel(String mobiel) {
        this.mobiel = mobiel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Date getBegindatumLid() {
        return begindatumLid;
    }

    public void setBegindatumLid(Date begindatumLid) {
        this.begindatumLid = begindatumLid;
    }

    public Date getEinddatumLid() {
        return einddatumLid;
    }

    public void setEinddatumLid(Date einddatumLid) {
        this.einddatumLid = einddatumLid;
    }

    public String getAanhef() {
        return aanhef;
    }

    public void setAanhef(String aanhef) {
        this.aanhef = aanhef;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getToevoegingAdres() {
        return toevoegingAdres;
    }

    public void setToevoegingAdres(String toevoegingAdres) {
        this.toevoegingAdres = toevoegingAdres;
    }

    public String getGeboorteplaats() {
        return geboorteplaats;
    }

    public void setGeboorteplaats(String geboorteplaats) {
        this.geboorteplaats = geboorteplaats;
    }

    public String getNationaliteit() {
        return nationaliteit;
    }

    public void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public Date getMutatieDatum() {
        return mutatieDatum;
    }

    public void setMutatieDatum(Date mutatieDatum) {
        this.mutatieDatum = mutatieDatum;
    }

    public String getRedenMutatie() {
        return redenMutatie;
    }

    public void setRedenMutatie(String redenMutatie) {
        this.redenMutatie = redenMutatie;
    }

    public String getSoortLid() {
        return soortLid;
    }

    public void setSoortLid(String soortLid) {
        this.soortLid = soortLid;
    }

    public String getSoortLidBond() {
        return soortLidBond;
    }

    public void setSoortLidBond(String soortLidBond) {
        this.soortLidBond = soortLidBond;
    }

    public String getSpelendLid() {
        return spelendLid;
    }

    public void setSpelendLid(String spelendLid) {
        this.spelendLid = spelendLid;
    }

    public String getNietSpelendLid() {
        return nietSpelendLid;
    }

    public void setNietSpelendLid(String nietSpelendLid) {
        this.nietSpelendLid = nietSpelendLid;
    }

    public String getTeam99() {
        return team99;
    }

    public void setTeam99(String team99) {
        this.team99 = team99;
    }

    public String getTrCo() {
        return trCo;
    }

    public void setTrCo(String trCo) {
        this.trCo = trCo;
    }

    public String getBegeleider() {
        return begeleider;
    }

    public void setBegeleider(String begeleider) {
        this.begeleider = begeleider;
    }

    public String getDonateur() {
        return donateur;
    }

    public void setDonateur(String donateur) {
        this.donateur = donateur;
    }

    public String getKaderlid() {
        return kaderlid;
    }

    public void setKaderlid(String kaderlid) {
        this.kaderlid = kaderlid;
    }

    public String getAdverteerder() {
        return adverteerder;
    }

    public void setAdverteerder(String adverteerder) {
        this.adverteerder = adverteerder;
    }

    public String getLvv() {
        return lvv;
    }

    public void setLvv(String lvv) {
        this.lvv = lvv;
    }

    public String getJaarLvv() {
        return jaarLvv;
    }

    public void setJaarLvv(String jaarLvv) {
        this.jaarLvv = jaarLvv;
    }

    public String getEreLid() {
        return ereLid;
    }

    public void setEreLid(String ereLid) {
        this.ereLid = ereLid;
    }

    public String getJaarEreLid() {
        return jaarEreLid;
    }

    public void setJaarEreLid(String jaarEreLid) {
        this.jaarEreLid = jaarEreLid;
    }

    public String getOmroepers() {
        return omroepers;
    }

    public void setOmroepers(String omroepers) {
        this.omroepers = omroepers;
    }

    public String getUmpire() {
        return umpire;
    }

    public void setUmpire(String umpire) {
        this.umpire = umpire;
    }

    public String getWedstrSecr() {
        return wedstrSecr;
    }

    public void setWedstrSecr(String wedstrSecr) {
        this.wedstrSecr = wedstrSecr;
    }

    public String getTweedePenningm() {
        return tweedePenningm;
    }

    public void setTweedePenningm(String tweedePenningm) {
        this.tweedePenningm = tweedePenningm;
    }

    public String getLedenadmin() {
        return ledenadmin;
    }

    public void setLedenadmin(String ledenadmin) {
        this.ledenadmin = ledenadmin;
    }

    public String getStrike() {
        return strike;
    }

    public void setStrike(String strike) {
        this.strike = strike;
    }

    public BigDecimal getContributie() {
        return contributie;
    }

    public void setContributie(BigDecimal contributie) {
        this.contributie = contributie;
    }

    public BigDecimal getKaderkorting() {
        return kaderkorting;
    }

    public void setKaderkorting(BigDecimal kaderkorting) {
        this.kaderkorting = kaderkorting;
    }

    public BigDecimal getGezinskorting() {
        return gezinskorting;
    }

    public void setGezinskorting(BigDecimal gezinskorting) {
        this.gezinskorting = gezinskorting;
    }

    public BsmTypeLeden getTldVolgnr() {
        return tldVolgnr;
    }

    public void setTldVolgnr(BsmTypeLeden tldVolgnr) {
        this.tldVolgnr = tldVolgnr;
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
        if (!(object instanceof BsmLeden)) {
            return false;
        }
        BsmLeden other = (BsmLeden) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmLeden[volgnr=" + volgnr + "]";
    }

}
