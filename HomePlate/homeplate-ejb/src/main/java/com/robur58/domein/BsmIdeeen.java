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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author godefrooije
 */
@Entity
@Table(name = "BSM_IDEEEN")
@NamedQueries({
    @NamedQuery(name = "BsmIdeeen.findAll", query = "SELECT b FROM BsmIdeeen b"),
    @NamedQuery(name = "BsmIdeeen.findByVolgnr", query = "SELECT b FROM BsmIdeeen b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmIdeeen.findByOpmerking", query = "SELECT b FROM BsmIdeeen b WHERE b.opmerking = :opmerking"),
    @NamedQuery(name = "BsmIdeeen.findByNaam", query = "SELECT b FROM BsmIdeeen b WHERE b.naam = :naam"),
    @NamedQuery(name = "BsmIdeeen.findByEmail", query = "SELECT b FROM BsmIdeeen b WHERE b.email = :email"),
    @NamedQuery(name = "BsmIdeeen.findByHomepagenaam", query = "SELECT b FROM BsmIdeeen b WHERE b.homepagenaam = :homepagenaam"),
    @NamedQuery(name = "BsmIdeeen.findByHomepage", query = "SELECT b FROM BsmIdeeen b WHERE b.homepage = :homepage"),
    @NamedQuery(name = "BsmIdeeen.findByDatum", query = "SELECT b FROM BsmIdeeen b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmIdeeen.findByTypeIdeeen", query = "SELECT b FROM BsmIdeeen b WHERE b.typeIdeeen = :typeIdeeen"),
    @NamedQuery(name = "BsmIdeeen.findByGbrVolgnr", query = "SELECT b FROM BsmIdeeen b WHERE b.gbrVolgnr = :gbrVolgnr")})
public class BsmIdeeen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IdeeenSequence")
    @SequenceGenerator(name="IdeeenSequence", sequenceName="IDN_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Size(max = 255)
    @Column(name = "OPMERKING")
    private String opmerking;
    @Size(max = 255)
    @Column(name = "NAAM")
    private String naam;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "HOMEPAGENAAM")
    private String homepagenaam;
    @Size(max = 255)
    @Column(name = "HOMEPAGE")
    private String homepage;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "TYPE_IDEEEN")
    private Long typeIdeeen;
    @Column(name = "GBR_VOLGNR")
    private Long gbrVolgnr;

    public BsmIdeeen() {
    }

    public BsmIdeeen(Long volgnr) {
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

    public Long getTypeIdeeen() {
        return typeIdeeen;
    }

    public void setTypeIdeeen(Long typeIdeeen) {
        this.typeIdeeen = typeIdeeen;
    }

    public Long getGbrVolgnr() {
        return gbrVolgnr;
    }

    public void setGbrVolgnr(Long gbrVolgnr) {
        this.gbrVolgnr = gbrVolgnr;
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
        if (!(object instanceof BsmIdeeen)) {
            return false;
        }
        BsmIdeeen other = (BsmIdeeen) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmIdeeen[ volgnr=" + volgnr + " ]";
    }
    
}
