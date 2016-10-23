/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.domein;

import java.io.Serializable;
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
 * @author godefrooije
 */
@Entity
@Table(name = "BSM_UPDATE_INFO")
@NamedQueries({
    @NamedQuery(name = "BsmVUpdateInfo.findAll", query = "SELECT b FROM BsmVUpdateInfo b"),
    @NamedQuery(name = "BsmVUpdateInfo.findByOmschrijving", query = "SELECT b FROM BsmVUpdateInfo b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmVUpdateInfo.findByOmschrijvingEn", query = "SELECT b FROM BsmVUpdateInfo b WHERE b.omschrijvingEn = :omschrijvingEn"),
    @NamedQuery(name = "BsmVUpdateInfo.findByDatum", query = "SELECT b FROM BsmVUpdateInfo b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmVUpdateInfo.findAllOrderByDatum", query = "SELECT b FROM BsmVUpdateInfo b ORDER BY b.datum desc")})
public class BsmVUpdateInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "DATUM")
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Column(name = "OMSCHRIJVING_EN")
    private String omschrijvingEn;

    public BsmVUpdateInfo() {
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getOmschrijvingEn() {
        return omschrijvingEn;
    }

    public void setOmschrijvingEn(String omschrijvingEn) {
        this.omschrijvingEn = omschrijvingEn;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
}
