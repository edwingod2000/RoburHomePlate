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
@Table(name = "GASTENBOEK")
@NamedQueries({
    @NamedQuery(name = "BsmGastenboek.findAll", query = "SELECT b FROM BsmGastenboek b WHERE b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByVolgnr", query = "SELECT b FROM BsmGastenboek b WHERE b.volgnr = :volgnr AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByOpmerking", query = "SELECT b FROM BsmGastenboek b WHERE b.opmerking = :opmerking AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByNaam", query = "SELECT b FROM BsmGastenboek b WHERE b.naam = :naam AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByEmail", query = "SELECT b FROM BsmGastenboek b WHERE b.email = :email AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByHomepagenaam", query = "SELECT b FROM BsmGastenboek b WHERE b.homepagenaam = :homepagenaam AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findByHomepage", query = "SELECT b FROM BsmGastenboek b WHERE b.homepage = :homepage AND b.typeGastenboek.volgnr = 1"),
    @NamedQuery(name = "BsmGastenboek.findHomeplateByJaar", query = "SELECT b FROM BsmGastenboek b WHERE b.typeGastenboek.volgnr = 1 AND b.datum >= :fromDate AND b.datum <= :toDate order by b.datum desc"),
    @NamedQuery(name = "BsmGastenboek.findByDatum", query = "SELECT b FROM BsmGastenboek b WHERE b.datum = :datum AND b.typeGastenboek.volgnr = 1")})
public class BsmGastenboek implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GastenboekSequence")
    @SequenceGenerator(name="GastenboekSequence", sequenceName="GBK_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "OPMERKING")
    private String opmerking;
    @Column(name = "NAAM")
    private String naam;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "HOMEPAGENAAM")
    private String homepagenaam;
    @Column(name = "HOMEPAGE")
    private String homepage;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @JoinColumn(name = "TYPE_GASTENBOEK", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmTypeGastenboek typeGastenboek;

    public BsmGastenboek() {
    }

    public BsmGastenboek(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepagenaam() {
        return homepagenaam;
    }

    public void setHomepagenaam(String homepagenaam) {
        this.homepagenaam = homepagenaam;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BsmTypeGastenboek getTypeGastenboek() {
        return typeGastenboek;
    }

    public void setTypeGastenboek(BsmTypeGastenboek typeGastenboek) {
        this.typeGastenboek = typeGastenboek;
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
        if (!(object instanceof BsmGastenboek)) {
            return false;
        }
        BsmGastenboek other = (BsmGastenboek) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmGastenboek[volgnr=" + volgnr + "]";
    }

}
