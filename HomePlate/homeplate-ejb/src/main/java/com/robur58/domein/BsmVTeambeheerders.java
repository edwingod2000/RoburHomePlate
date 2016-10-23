/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "BSM_TEAMBEHEERDERS")
@NamedQueries({
    @NamedQuery(name = "BsmVTeambeheerders.findAll", query = "SELECT b FROM BsmVTeambeheerders b"),
    @NamedQuery(name = "BsmVTeambeheerders.findByVolgnr", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmVTeambeheerders.findByTemVolgnr", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.temVolgnr = :temVolgnr"),
    @NamedQuery(name = "BsmVTeambeheerders.findByVoornaam", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.voornaam = :voornaam"),
    @NamedQuery(name = "BsmVTeambeheerders.findByTussenvoegsel", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.tussenvoegsel = :tussenvoegsel"),
    @NamedQuery(name = "BsmVTeambeheerders.findByNaam", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.naam = :naam"),
    @NamedQuery(name = "BsmVTeambeheerders.findByGebruikersid", query = "SELECT b FROM BsmVTeambeheerders b WHERE b.gebruikersid = :gebruikersid")})
public class BsmVTeambeheerders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "VOLGNR")
    @Id
    private BigInteger volgnr;
    @Basic(optional = false)
    @Column(name = "TEM_VOLGNR")
    private long temVolgnr;
    @Column(name = "VOORNAAM")
    private String voornaam;
    @Column(name = "TUSSENVOEGSEL")
    private String tussenvoegsel;
    @Column(name = "NAAM")
    private String naam;
    @Basic(optional = false)
    @Column(name = "GEBRUIKERSID")
    private String gebruikersid;

    public BsmVTeambeheerders() {
    }

    public BigInteger getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(BigInteger volgnr) {
        this.volgnr = volgnr;
    }

    public long getTemVolgnr() {
        return temVolgnr;
    }

    public void setTemVolgnr(long temVolgnr) {
        this.temVolgnr = temVolgnr;
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

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGebruikersid() {
        return gebruikersid;
    }

    public void setGebruikersid(String gebruikersid) {
        this.gebruikersid = gebruikersid;
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
        if (naam != null) {
            builder.append(" ");
            builder.append(naam);
        }
        return builder.toString();
    }
    

}
